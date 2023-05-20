package org.khasanof.webflux;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 17.05.2023
 * <br/>
 * Time: 15:43
 * <br/>
 * Package: org.khasanof.webflux
 */
@Slf4j
public class TimeoutTests {

    @Test
    void simpleTimeoutSuccessTest() {
        Mono<Response> mono = Mono.just("Jeck")
                .publishOn(Schedulers.boundedElastic())
                .handle((m, sink) -> {
                    try {
                        Thread.sleep(1400);
                    } catch (InterruptedException e) {
                        sink.error(new RuntimeException(e));
                    }
                    sink.next(m.length());
                }).timeout(Duration.ofSeconds(2))
                .doOnError(ex -> log.warn("Timeout Exception !"))
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof TimeoutException)
                        .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> {
                            throw new RuntimeException("External Service failed to process after max retries");
                        }))
                ).doOnNext(o -> log.info("Service Working Well!"))
                .map(o -> new Response(true, "OK"))
                .doOnError(ex -> log.info("Exception : {}", ex.getMessage()))
                .onErrorResume(ex -> Mono.just(new Response(false, ex.getMessage())));

        StepVerifier.create(mono)
                .assertNext(next -> {
                    assertTrue(next.success);
                    assertEquals(next.message, "OK");
                })
                .expectComplete()
                .verify();
    }

    @Test
    void simpleTimeoutFailTest() {
        Mono<Response> mono = Mono.just("Jeck")
                .publishOn(Schedulers.boundedElastic())
                .handle((m, sink) -> {
                    try {
                        Thread.sleep(2400);
                    } catch (InterruptedException e) {
                        sink.error(new RuntimeException(e));
                    }
                    sink.next(m.length());
                }).timeout(Duration.ofSeconds(2))
                .doOnError(ex -> log.warn("Timeout Exception !"))
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof TimeoutException)
                        .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> {
                            throw new RuntimeException("External Service failed to process after max retries");
                        }))
                ).doOnNext(o -> log.info("Service Working Well!"))
                .map(o -> new Response(true, "OK"))
                .doOnError(ex -> log.info("Exception : {}", ex.getMessage()))
                .onErrorResume(ex -> Mono.just(new Response(false, ex.getMessage())));

        StepVerifier.create(mono)
                .assertNext(next -> {
                    assertFalse(next.success);
                    assertEquals(next.message, "External Service failed to process after max retries");
                })
                .expectComplete()
                .verify();
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    static class Response {
        private boolean success;
        private String message;
    }

}
