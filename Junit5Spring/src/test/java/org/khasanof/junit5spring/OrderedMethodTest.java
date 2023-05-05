package org.khasanof.junit5spring;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:18
 * <br/>
 * Package: org.khasanof.junit5spring
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedMethodTest {

    @Test
    @Order(3)
    void nullValues() {
        // perform assertions against null values
    }

    @Test
    @Order(2)
    void emptyValues() {
        // perform assertions against empty values
    }

    @Test
    @Order(1)
    void validValues() {
        // perform assertions against valid values
    }

}
