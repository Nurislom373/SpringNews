package org.khasanof.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.stream.IntStream;

/**
 * @author Nurislom
 * @see org.khasanof.webflux
 * @since 13.07.2023 9:37
 */
@Slf4j
public class FluxPushCreateFactoryMethodTest {

    @Test
    void pushMethodTest() {
        Flux.push(emitter -> {
                    emitter.onDispose(() -> log.info("Disposed"));
                    IntStream.range(2000, 3000)
                            .forEach(emitter::next);
                }).delayElements(Duration.ofMillis(1))
                .subscribe(e -> log.info("onNext : {}", e));
    }

    @Test
    void createMethodTest() {
        Flux.create(emitter -> {
                    emitter.onDispose(() -> log.info("Disposed"));
                    IntStream.range(200, 300)
                            .peek(peek -> {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .forEach(emitter::next);
                }).delaySequence(Duration.ofMillis(100))
                .subscribe(e -> log.info("onNext: {}", e));
    }

    @Test
    void generateMethodTest() {
        Flux.generate(() -> Tuples.of(0L, 1L),
                        (state, sink) -> {
                            log.info("generated value: {}", state.getT2());
                            sink.next(state.getT2());
                            long newValue = state.getT1() + state.getT2();
                            return Tuples.of(state.getT2(), newValue);
                        }).delaySequence(Duration.ofMillis(1))
                .take(7)
                .subscribe(e -> log.info("onNext: {}", e));
    }

}
