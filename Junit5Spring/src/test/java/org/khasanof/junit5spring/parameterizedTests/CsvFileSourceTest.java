package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12.05.2023
 * <br/>
 * Time: 9:59
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/two_column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFile(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two_column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFileS(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

}
