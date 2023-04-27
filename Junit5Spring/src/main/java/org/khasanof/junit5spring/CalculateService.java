package org.khasanof.junit5spring;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/27/2023
 * <br/>
 * Time: 9:34 AM
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class CalculateService {

    long calculate(long num1, long num2, char oper) {
        return switch (oper) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '/' -> num1 / num2;
            case '*' -> num1 * num2;
            default -> throw new RuntimeException("Unsupported Operator!");
        };
    }

}
