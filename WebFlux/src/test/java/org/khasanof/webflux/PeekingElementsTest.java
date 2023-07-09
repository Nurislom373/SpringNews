package org.khasanof.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

/**
 * @author Nurislom
 * @see org.khasanof.webflux
 * @since 09.07.2023 11:54
 */
@Slf4j
public class PeekingElementsTest {

    @Test
    void simplePeekingTest() {
        Flux.just(1, 2, 3)
                .concatWith(Flux.error(new RuntimeException("Conn error")))
                .doOnEach(s -> log.info("signal: {}", s))
                .subscribe();
    }

    @Test
    void meteriliazeingTest() {
        Flux.range(1, 3)
                .doOnNext(e -> log.info("data : {}", e))
                .materialize()
                .doOnNext(e -> log.info("signal: {}", e))
                .dematerialize()
                .map(obj -> (Integer) obj)
                .collectList()
                .subscribe(r-> log.info("result: {}", r));
    }

}
