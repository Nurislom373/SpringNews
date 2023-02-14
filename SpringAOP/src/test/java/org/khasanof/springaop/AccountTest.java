package org.khasanof.springaop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.khasanof.springaop.aspect.account.Account;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 4:14 PM
 * <br/>
 * Package: org.khasanof.springaop
 */
@SpringBootTest
public class AccountTest {

    private Account account;

    @BeforeEach
    public void before() {
        account = new Account();
    }

    @Test
    public void given20AndMin10_whenWithdraw5_thenSuccess() {
        Assertions.assertTrue(account.withDraw(5));
    }

    @Test
    public void given20AndMin10_whenWithdraw100_thenFail() {
        Assertions.assertFalse(account.withDraw(100));
    }
}
