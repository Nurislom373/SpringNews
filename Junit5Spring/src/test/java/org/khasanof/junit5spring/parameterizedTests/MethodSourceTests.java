package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 11.05.2023
 * <br/>
 * Time: 8:59
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class MethodSourceTests {

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana", "noom");
    }

    /**
     * If you do not explicitly provide a factory method name via @MethodSource, JUnit Jupiter will search for a factory
     * method that has the same name as the current @ParameterizedTest method by convention. This is demonstrated in the
     * following example.
     */
    @ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> testWithDefaultLocalMethodSource() {
        return Stream.of("apple", "banana");
    }

    /**
     * Streams for primitive types (DoubleStream, IntStream, and LongStream) are also supported as demonstrated by the
     * following example.
     */
    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    /**
     * If a parameterized test method declares multiple parameters, you need to return a collection, stream, or array of
     * Arguments instances or object arrays as shown below (see the Javadoc for @MethodSource for further details on
     * supported return types). Note that arguments(Object…​) is a static factory method defined in the Arguments
     * interface. In addition, Arguments.of(Object…​) may be used as an alternative to arguments(Object…​).
     */
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >= 1 && num <= 2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
                Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @MethodSource("org.khasanof.junit5spring.parameterizedTests.StringProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        assertFalse(tinyString.isEmpty());
    }

}
