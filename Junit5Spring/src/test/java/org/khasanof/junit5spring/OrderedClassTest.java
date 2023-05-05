package org.khasanof.junit5spring;

import org.junit.jupiter.api.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 23:22
 * <br/>
 * Package: org.khasanof.junit5spring
 */
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class OrderedClassTest {

    @Nested
    @Order(1)
    class PrimaryTests {

        @Test
        void test1() {
        }
    }

    @Nested
    @Order(2)
    class SecondaryTests {

        @Test
        void test2() {
        }
    }

}
