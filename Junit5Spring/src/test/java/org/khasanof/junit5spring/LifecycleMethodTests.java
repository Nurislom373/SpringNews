package org.khasanof.junit5spring;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/29/2023
 * <br/>
 * Time: 8:27 PM
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class LifecycleMethodTests {

    private static final Logger log = LoggerFactory.getLogger(LifecycleMethodTests.class);

    @BeforeAll
    static void initAll() {
        log.info("initAll Method Start");
    }

    @BeforeEach
    void init() {
        log.info("init Method Start");
    }

    @Test
    void failingTest() {
        log.error("failingTest Method Start");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed!
    }

    @Test
    void abortedTest() {
        log.info("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        log.warn("tearDown Method Start");
    }

    @AfterAll
    static void tearDownAll() {
        log.warn("tearDownAll Method Start");
    }

}
