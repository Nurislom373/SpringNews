package org.khasanof.junit5spring.extensions;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Nurislom
 * <br/>
 * Date: 06.05.2023
 * <br/>
 * Time: 21:29
 * <br/>
 * Package: org.khasanof.junit5spring.extensions
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifeCycleLogger {

    Logger logger = LoggerFactory.getLogger(TestLifeCycleLogger.class.getName());

    @BeforeAll
    default void beforeAllTests() {
        logger.info("Before all tests");
    }

    @AfterAll
    default void afterAllTests() {
        logger.info("After all tests");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        logger.info(String.format("About to execute [%s]", testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        logger.info(String.format("Finished executing [%s]", testInfo.getDisplayName()));
    }


}
