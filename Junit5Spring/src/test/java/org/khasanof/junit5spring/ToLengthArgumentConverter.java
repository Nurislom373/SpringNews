package org.khasanof.junit5spring;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;

/**
 * Author: Nurislom
 * <br/>
 * Date: 16.05.2023
 * <br/>
 * Time: 9:32
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer> {

    /**
     * Create a new {@code TypedArgumentConverter}.
     */
    protected ToLengthArgumentConverter() {
        super(String.class, Integer.class);
    }

    @Override
    protected Integer convert(String source) throws ArgumentConversionException {
        return (source != null ? source.length() : 0);
    }
}
