package org.khasanof.junit5spring.truthAssertions;

import com.google.common.truth.Truth;
import com.google.common.truth.TruthJUnit;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 01.05.2023
 * <br/>
 * Time: 14:39
 * <br/>
 * Package: org.khasanof.junit5spring.truthAssertions
 */
public class TruthAssertionsTest {

    // Truth ni Asserion sifatida ishlaganimizda method public bo'lishi kerak!6.
    @Test
    public void simpleAssertions() {
        String var = "awesome";

        Truth.assertThat(var).startsWith("awe");
        Truth.assertWithMessage("Without me, it's just aweso")
                .that(var)
                .contains("me");
    }

    @Test
    public void iterableAssertions() {
        List<String> list = List.of("RED", "YELLOW", "BLUE", "ORANGE", "GREEN");

        Truth.assertThat(list)
                .containsExactly("RED", "YELLOW", "BLUE", "ORANGE", "GREEN")
                .inOrder();
    }

    @Test
    public void optionalTest() {
        Optional<String> optional = Optional.of("Jack");

        TruthJUnit.assume().that(optional)
                .isSameInstanceAs(Optional.class);
    }


}
