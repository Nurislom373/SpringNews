package org.khasanof.junit5spring.thirdPartyAssertions;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.khasanof.junit5spring.CalculateService;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/29/2023
 * <br/>
 * Time: 9:20 PM
 * <br/>
 * Package: org.khasanof.junit5spring.thirdPartyAssertions
 */
public class HamcrestAssertionsTest {

    private final CalculateService service = new CalculateService();

    @Test
    void assertWithHamcrest() {
        Assertions.assertThat(service.calculate(1, 1, '+'))
                .isEqualTo(2)
                .isLessThan(3)
                .isNotNull();
    }

}
