package org.khasanof.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 19:09
 * <br/>
 * Package: org.khasanof.webflux
 */
@Slf4j
public class FilterWebFluxTest {

    @Test
    void test() {
        Flux<Integer> numbers = Flux.range(1, 10);

        Flux<Integer> filteredNumbers = numbers.filter(n -> n % 2 == 0);

        filteredNumbers.subscribe(System.out::println);
    }

    @Test
    void reducingTest() {
        Flux.just(3, 5, 7, 9, 11, 15, 16, 17)
                .any(e -> e % 2 == 0)
                .subscribe(hasEvens -> log.info("Has evens: {}", hasEvens));
    }

    @Test
    void reduce() {
        Flux.range(1, 5)
                .reduce(0, (acc, elem) -> acc + elem)
                .subscribe(result -> log.info("Result: {}", result));

    }

    @Test
    void scanOperatorTest() {
        Flux.range(1, 5)
                .scan(0, (acc, elem) -> acc + elem)
                .subscribe(result -> log.info("Result: {}", result));
    }

    @Test
    void bucketSize() {
        int bucketSize = 5; // (1)
        Flux.range(1, 500) // (2)
                .index() // (3)
                .scan( // (4)
                        new int[bucketSize], // (4.1)
                        (acc, elem) -> { //
                            acc[(int) (elem.getT1() % bucketSize)] = elem.getT2(); // (4.2)
                            return acc; // (4.3)
                        })
                .skip(bucketSize) // (5)
                .map(array -> Arrays.stream(array).sum() * 1.0 / bucketSize) // (6)
                .subscribe(av -> log.info("Running average: {}", av));
    }

    @Test
    void bufferTest() {
        Flux.range(1, 13)
                .buffer(4)
                .subscribe(e -> log.info("onNext: {}", e));
    }

    @Test
    void windowUntil() {
        Flux<Integer> numbers = Flux.range(1, 100);

        numbers.windowUntil(num -> num % 10 == 0)
                .flatMap(Flux::collectList) // Collect elements of each window into a list
                .subscribe(System.out::println);
    }

    @Test
    void windowUntilChanged() {
        Flux<String> words = Flux.just("big","apple", "banana", "banana", "cherry", "cherry", "cherry");

        words.windowUntilChanged(word -> word.length())
                .flatMap(window -> window.collectList())// Collect elements of each window into a list
                .subscribe(System.out::println);
    }


}
