package org.khasanof.junit5spring.parameterizedTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12.05.2023
 * <br/>
 * Time: 9:18
 * <br/>
 * Package: org.khasanof.junit5spring.parameterizedTests
 */
public class CsvSourceTest {

    @ParameterizedTest
    @CsvSource({
            "apple, 1",
            "banana, 2",
            "lemon, 3"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            FRUIT,         RANK
            apple,         1
            banana,        2
            'lemon, lime', 0xF1
            strawberry,    700_000
            """)
    void testWithTextBlockCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

}
