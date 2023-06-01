package org.khasanof.junit5spring.dynamicTests;

import org.assertj.core.api.ThrowingConsumer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 31.05.2023
 * <br/>
 * Time: 17:09
 * <br/>
 * Package: org.khasanof.junit5spring.dynamicTests
 */
public class DynamicTestsDemo {

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return List.of(
                DynamicTest.dynamicTest("1st dynamic test", () -> assertNotEquals(1,
                        new Random().nextInt(99999)))
        );
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestsFromIterable() {
        return List.of(
                DynamicTest.dynamicTest("1st dynamic test", () -> assertNotEquals(1,
                        new Random().nextInt(99999)))
        );
    }

    @TestFactory
    Iterator<DynamicTest> dynamicTestsFromIterator() {
        return List.of(
                DynamicTest.dynamicTest("1st dynamic test", () -> assertNotEquals(1,
                        new Random().nextInt(99999)))
        ).iterator();
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Stream.of(
                DynamicTest.dynamicTest("1st dynamic test", () -> assertNotEquals(1,
                        new Random().nextInt(99999)))
        );
    }

}
