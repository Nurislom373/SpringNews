package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 13.05.2023
 * <br/>
 * Time: 5:37
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class ArgumentSourceTest {

    @ParameterizedTest
    @ArgumentsSource(ArgumentSourceProvider.class)
    void testWithArgumentSource(String name) {
        assertNotNull(name);
    }

}
