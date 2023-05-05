package org.khasanof.junit5spring.DIandCI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:48
 * <br/>
 * Package: org.khasanof.junit5spring.DIandCI
 */
@DisplayName("Depdency Injection Test")
public class DependencyInjectionTest {

    @BeforeEach
    void testInfo(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        System.out.println("displayName = " + displayName);
    }

    @Test
    void run() {

    }

}
