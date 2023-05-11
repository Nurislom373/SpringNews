package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 11.05.2023
 * <br/>
 * Time: 8:38
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class ParameterizedTests {

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 10})
    void isSubtract(Integer num) {
        assertEquals(0, num % 2);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    /**
     * @EnumSource provides a convenient way to use Enum constants.
     */
    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        assertNotNull(unit);
    }

    /**
     * The annotation provides an optional names attribute that lets you specify which constants shall be used, like in
     * the following example. If omitted, all constants will be used.
     */
    @ParameterizedTest
    @EnumSource
    void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
        assertNotNull(unit);
    }

    @ParameterizedTest
    @EnumSource(names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(ChronoUnit unit) {
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = { "ERAS", "FOREVER" })
    void testWithEnumSourceExclude(ChronoUnit unit) {
        assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.MATCH_ALL, names = "^.*DAYS$")
    void testWithEnumSourceRegex(ChronoUnit unit) {
        assertTrue(unit.name().endsWith("DAYS"));
    }

}
