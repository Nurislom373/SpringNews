package org.khasanof.junit5spring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Author: Nurislom
 * <br/>
 * Date: 05.05.2023
 * <br/>
 * Time: 22:56
 * <br/>
 * Package: org.khasanof.junit5spring
 */
@Disabled("Disabled until bug #99 has been fixed")
public class DisabledTests {

    @Test
    void run() {
        System.out.println("Hello World!");
    }

}
