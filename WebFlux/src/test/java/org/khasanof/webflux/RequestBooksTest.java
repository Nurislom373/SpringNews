package org.khasanof.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

/**
 * Author: Nurislom
 * <br/>
 * Date: 22.06.2023
 * <br/>
 * Time: 9:41
 * <br/>
 * Package: org.khasanof.webflux
 */
@Slf4j
public class RequestBooksTest {

    private final Random random = new Random();

    @Test
    public void test() {
        Flux.just("user-1", "user-2", "user-3")
                .flatMap(u -> requestBooks(u)
                        .map(b -> u + "/" + b)
                        .log())
                .log()
                .subscribe(r -> log.info("onNext: {}", r));
    }

    public Flux<String> requestBooks(String user) {
        return Flux.range(1, random.nextInt(3) + 1) // (1)
                .map(i -> "book-" + i) // (2)
                .delayElements(Duration.ofMillis(3)); // (3)
    }

    @Test
    void sampleTest() {
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(1))
                .sample(Duration.ofMillis(20))
                .doOnNext(next -> log.debug("onNext : " + next))
                .log()
                .subscribe(e -> log.debug("onNext: {}", e));
    }

}
