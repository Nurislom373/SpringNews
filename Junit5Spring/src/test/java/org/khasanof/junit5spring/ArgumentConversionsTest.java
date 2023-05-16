package org.khasanof.junit5spring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.khasanof.junit5spring.classes.Book;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 16.05.2023
 * <br/>
 * Time: 8:46
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class ArgumentConversionsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void isNotNullTest(float num) {
        assertNotEquals(num, 4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2017-03-14", "2018-03-14"})
    void isNotNullLocaDateTest(LocalDate date) {
        assertThat(date).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Booms 48"})
    void testWithImplicitFallbackArgumentConversion(Book book) {
        assertEquals("Booms 48", book.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = {"jeck", "hello"})
    void testToLengthConversions(@ConvertWith(ToLengthArgumentConverter.class) Integer length) {
        System.out.println("length = " + length);
        assertNotNull(length);
    }

}
