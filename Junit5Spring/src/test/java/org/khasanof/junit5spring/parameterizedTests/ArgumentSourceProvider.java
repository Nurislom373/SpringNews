package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 13.05.2023
 * <br/>
 * Time: 5:38
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class ArgumentSourceProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of("Nurislom", "khasanof").map(Arguments::of);
    }
}
