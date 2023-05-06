package org.khasanof.junit5spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.junit5spring.extensions.TestLifeCycleLogger;
import org.khasanof.junit5spring.extensions.TimingExtension;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:52
 * <br/>
 * Package: org.khasanof.junit5spring
 */
@ExtendWith(TimingExtension.class)
public class TestInterfaceDemoTest implements TestLifeCycleLogger {

    @Test
    void isEqualValue() {
        assertEquals(1, "a".length(), "is always equal");
    }

}
