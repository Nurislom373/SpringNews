package org.khasanof.junit5spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 08.05.2023
 * <br/>
 * Time: 10:08
 * <br/>
 * Package: org.khasanof.junit5spring
 */
/*
    JUnit Jupiter provides the ability to repeat a test a specified number of times by annotating a method with
    @RepeatedTest and specifying the total number of repetitions desired. Each invocation of a repeated test behaves
    like the execution of a regular @Test method with full support for the same lifecycle callbacks and extensions.
 */
public class RepeatedTestDemoTests {

    @RepeatedTest(value = 10)
    void repeatedTest() {
        Random random = new Random();
        assertAll(
                () -> assertNotEquals(random.nextInt(100), random.nextInt(100, 1000))
        );
    }

    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals("Repeat! 1/1", testInfo.getDisplayName());
    }

    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
    }

}
