package org.khasanof.junit5spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/27/2023
 * <br/>
 * Time: 9:37 AM
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class JunitSimpleTest {

    @Test
    void addition() {
        CalculateService service = new CalculateService();
        long calculate = service.calculate(5, 5, '+');
        Assertions.assertEquals(10, calculate);
    }

}
