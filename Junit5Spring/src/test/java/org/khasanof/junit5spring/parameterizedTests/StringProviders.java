package org.khasanof.junit5spring.parameterizedTests;

import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 11.05.2023
 * <br/>
 * Time: 9:06
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class StringProviders {

    static Stream<String> tinyStrings() {
        return Stream.of("Javohir", "Shoxista", "Nurislom");
    }

}
