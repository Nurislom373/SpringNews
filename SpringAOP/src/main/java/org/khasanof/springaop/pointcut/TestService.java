package org.khasanof.springaop.pointcut;

import org.springframework.stereotype.Service;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/18/2023
 * <br/>
 * Time: 11:00 AM
 * <br/>
 * Package: org.khasanof.springaop.config.aspc
 */
@Service
public class TestService {

    @Log
    public void stuff() {
        System.out.println("In Service");
    }

    public void observer() {
        System.out.println("Execute Observer");
    }

    public Object exceptionMethod() {
        System.out.println("Hello World");
        throw new RuntimeException("Hello");
    }
}
