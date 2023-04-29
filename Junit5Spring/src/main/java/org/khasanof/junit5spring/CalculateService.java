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

    public String greeting() {
        return "Boom";
    }

    public long calculate(long num1, long num2, char oper) {
        return switch (oper) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '/' -> divide(num1, num2);
            case '*' -> num1 * num2;
            default -> throw new RuntimeException("Unsupported Operator!");
        };
    }

    private long divide(long var1, long var2) {
        if (var2 == 0 || var1 == 0) {
            throw new ArithmeticException("cannot be divided by zero.");
        }
        return var1 / var2;
    }

}
