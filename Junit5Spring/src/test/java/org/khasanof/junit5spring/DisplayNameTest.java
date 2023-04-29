package org.khasanof.junit5spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/29/2023
 * <br/>
 * Time: 8:44 PM
 * <br/>
 * Package: org.khasanof.junit5spring
 */
@DisplayName("Display Name Test Class")
public class DisplayNameTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }

}
