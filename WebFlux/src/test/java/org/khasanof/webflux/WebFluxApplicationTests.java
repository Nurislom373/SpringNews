package org.khasanof.webflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ConnectableFlux;
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

    /*
        The repeat() method in the Flux library of Reactor provides a way to repeat the elements emitted by a Flux
        sequence a specified number of times. This method can be useful in scenarios where you need to repeat a sequence
        of events multiple times, for example, when testing or when you want to retry an operation that has failed.

        You can use the repeat() method after defining a sequence of events using the Flux library. The method takes an
        integer parameter that indicates the number of times you want to repeat the sequence. If you pass a negative
        value or zero, it will immediately complete the sequence without emitting any values.
     */
    @Test
    void test_FluxRepeatMethod() {
        Flux.range(1, 3)
                .repeat(2)
                .subscribe(System.out::println);
    }

    @Test
    void test_ConnectableFluxClass() {
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
            for (int i = 0; i < 5; i++) {
                fluxSink.next(System.currentTimeMillis());
            }
        }).publish();

        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);
    }

    @Test
    void test_FluxRangeMethodOnError() {
        Flux<Integer> flux = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got To 4");
                });
        flux.subscribe(System.out::println, error -> System.out.println("Error :" + error));

        Flux<Integer> flux2 = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got To 4");
                });
        flux2.subscribe(System.out::println,
                error -> System.out.println("Error :" + error),
                () -> System.out.println("Done"));
    }

    @Test
    void test_SampleSubscriber() {
        SampleSubscriber<Integer> subscriber = new SampleSubscriber<>();
        Flux<Integer> flux = Flux.range(1, 4);
        flux.subscribe(subscriber);
    }

    @Test
    void test_BaseSubscriberCancel() {
        Flux.range(1, 10)
                .doOnRequest(System.out::println)
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Cancelling after having received " + value );
                        cancel();
                    }
                });
    }


}
