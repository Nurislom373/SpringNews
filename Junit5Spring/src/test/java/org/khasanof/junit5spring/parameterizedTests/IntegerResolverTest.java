package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12.05.2023
 * <br/>
 * Time: 9:15
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class IntegerResolverTest {

    @RegisterExtension
    static final IntegerResolver integerResolver = new IntegerResolver();

    @ParameterizedTest
    @MethodSource("factoryMethodWithArguments")
    void testWithFactoryMethodWithArguments(String argument) {
        assertTrue(argument.startsWith("2"));
    }

    static Stream<Arguments> factoryMethodWithArguments(int quantity) {
        return Stream.of(
                arguments(quantity + " apples"),
                arguments(quantity + " lemons")
        );
    }

}
