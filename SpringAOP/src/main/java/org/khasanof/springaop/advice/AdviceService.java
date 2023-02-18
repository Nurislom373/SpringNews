package org.khasanof.springaop.advice;

import org.springframework.stereotype.Service;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/18/2023
 * <br/>
 * Time: 9:58 PM
 * <br/>
 * Package: org.khasanof.springaop.advice
 */
@Service
public class AdviceService {

    public void checkout() {
        System.out.println("Add Checkout!");
    }

    public void testMethod() {
        System.out.println("Test Method Calledd");
    }

    public void helloWorld() {
        System.out.println("Hello World!");
    }

}
