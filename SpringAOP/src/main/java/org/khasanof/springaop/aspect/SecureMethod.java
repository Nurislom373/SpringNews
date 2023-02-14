package org.khasanof.springaop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 3:40 PM
 * <br/>
 * Package: org.khasanof.springaop.aspect
 */
public class SecureMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureMethod.class);

    @Secured(isLocked = true)
    public void lockMethod() throws Exception {
        LOGGER.info("LockedMethod");
    }

    @Secured
    public void unlockedMethod() throws Exception {
        LOGGER.info("UnlockedMethod");
    }

    public static void main(String[] args) throws Exception {
        SecureMethod method = new SecureMethod();
        method.lockMethod();
    }
}
