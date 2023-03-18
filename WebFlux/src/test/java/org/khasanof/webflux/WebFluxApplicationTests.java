package org.khasanof.webflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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

    @Test
    void doesntSubscribe() {
        Flux<String> flux = Flux.just("red", "white", "blue");

        flux.log().map(String::toUpperCase)
                .log("to UpperCase")
                .subscribe(System.out::println);
    }

    @Test
    void test_subscribers() {
        Flux.just("red", "white", "blue")
                .log("Start Flux")
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .subscribe(System.out::println);
    }

}
