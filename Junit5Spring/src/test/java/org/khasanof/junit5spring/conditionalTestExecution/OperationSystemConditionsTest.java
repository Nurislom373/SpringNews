package org.khasanof.junit5spring.conditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:00
 * <br/>
 * Package: org.khasanof.junit5spring.conditionalTestExecution
 */
public class OperationSystemConditionsTest {

    private static final Logger logger = LoggerFactory.getLogger(OperationSystemConditionsTest.class);

    @Test
    @EnabledOnOs(OS.MAC)
    void onlyMacOSTest() {
        logger.info("This Test Execute Only MAC OS system!");
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void onlyLinuxOrMacTest() {
        logger.info("This Test Execute Linux or MAC OS system");
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnWindows() {
        logger.info("This Test Execute other than windows");
    }

}
