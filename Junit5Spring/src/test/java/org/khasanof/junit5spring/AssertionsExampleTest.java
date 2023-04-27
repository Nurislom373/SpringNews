package org.khasanof.junit5spring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/27/2023
 * <br/>
 * Time: 9:58 AM
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class AssertionsExampleTest {

    private final CalculateService service = new CalculateService();
    private final Person person = new Person("Nurislom", 18);

    @Test
    void standardAssertions() {
        assertEquals(4, service.calculate(2, 2, '+'));
        assertEquals(5, service.calculate(10, 5, '-'));
        assertTrue('a' < 'b', "Assertion messages can be lazily evaluated -- " +
                "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all failures will be reported together.
        assertAll(
                () -> assertEquals("Nurislom", person.getName()),
                () -> assertEquals(18, person.getAge()),
                () -> assertEquals(5, 5)
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the subsequent code in the same block will be skipped.
        assertAll(
                () -> {
                    String name = person.getName();
                    assertNotNull(name);

                    // Executed only if the previous assertion is valid.
                    assertAll(name,
                            () -> assertTrue(name.startsWith("N")),
                            () -> assertTrue(name.endsWith("m"))
                    );
                },
                () -> {
                    Integer age = person.getAge();
                    assertNotNull(age);

                    // Executed only if the previous assertion is valid.
                    assertAll(String.valueOf(age),
                            () -> assertEquals(18, (int) age),
                            () -> assertTrue(true)
                    );
                }
        );
    }

}
