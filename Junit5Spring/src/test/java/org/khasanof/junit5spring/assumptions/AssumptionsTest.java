package org.khasanof.junit5spring.assumptions;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.khasanof.junit5spring.CalculateService;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 22:50
 * <br/>
 * Package: org.khasanof.junit5spring.assumptions
 */
public class AssumptionsTest {

    private final CalculateService service = new CalculateService();

    @Test
    void testOnlyOnCIServer() {
        Assumptions.assumeTrue("CI".equals(System.getenv("ENV")));
    }

}
