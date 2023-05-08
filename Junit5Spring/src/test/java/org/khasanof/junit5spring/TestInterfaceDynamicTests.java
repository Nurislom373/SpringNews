package org.khasanof.junit5spring;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 08.05.2023
 * <br/>
 * Time: 10:02
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public interface TestInterfaceDynamicTests {

    @TestFactory
    default Stream<DynamicTest> dynamicTestForIsStartWithA() {
        return Stream.of("acer", "adar", "above", "alex")
                .map(text -> DynamicTest.dynamicTest(text, () -> assertTrue(text.startsWith("a"))));
    }

}
