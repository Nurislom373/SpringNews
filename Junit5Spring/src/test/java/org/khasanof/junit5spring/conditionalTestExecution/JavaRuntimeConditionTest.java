package org.khasanof.junit5spring.conditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:07
 * <br/>
 * Package: org.khasanof.junit5spring.conditionalTestExecution
 */
public class JavaRuntimeConditionTest {

    private static final Logger logger = LoggerFactory.getLogger(OperationSystemConditionsTest.class);

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava() {
        logger.info("This Test Execute Only JRE 8, i.e Java 8 version");
    }

    @Test
    @EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10 })
    void onJava9Or10() {
        // ...
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_11)
    void fromJava9to11() {
        // ...
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_9)
    void fromJava9toCurrentJavaFeatureNumber() {
        // ...
    }

    @Test
    @EnabledForJreRange(max = JRE.JAVA_11)
    void fromJava8To11() {
        // ...
    }

    @Test
    @DisabledOnJre(JRE.JAVA_9)
    void notOnJava9() {
        // ...
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_11)
    void notFromJava9to11() {
        // ...
    }

    @Test
    @DisabledForJreRange(min = JRE.JAVA_9)
    void notFromJava9toCurrentJavaFeatureNumber() {
        // ...
    }

    @Test
    @DisabledForJreRange(max = JRE.JAVA_11)
    void notFromJava8to11() {
        // ...
    }

}
