package org.khasanof.webflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

class WebFluxApplicationTests {

    @Test
    void contextLoads() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.subscribe(System.out::println);
    }

    @Test
    void contextMono() {
        Mono<String> mono = Mono.just("Hello, World!");

        mono.subscribe(System.out::println);
    }

    @Test
    void subscribeElements() {
        List<Integer> elements = new ArrayList<>();

        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements::add);

        Assertions.assertThat(elements).containsExactly(1, 2, 3, 4);
    }

}
