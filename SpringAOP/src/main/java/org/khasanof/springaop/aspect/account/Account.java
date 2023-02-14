package org.khasanof.springaop.aspect.account;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 3:38 PM
 * <br/>
 * Package: org.khasanof.springaop.aspect
 */
public class Account {

    int balance = 20;

    public boolean withDraw(int amount) {
        if (balance < amount) {
            return false;
        }

        balance = balance - amount;
        return true;
    }
}
