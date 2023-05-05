package org.khasanof.junit5spring.conditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:13
 * <br/>
 * Package: org.khasanof.junit5spring.conditionalTestExecution
 */
public class CustomConditionTest {

    @Test
    @EnabledIf("customCondition")
    void enabled() {
        // ...
    }

    @Test
    @DisabledIf("customCondition")
    void disabled() {
        // ...
    }

    boolean customCondition() {
        return true;
    }

}
