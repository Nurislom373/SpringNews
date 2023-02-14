package org.khasanof.springexpressionlanguage.operators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:18 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage
 * <br/>
 * <br/>
 * SpEL supports all basic arithmetic operators:
 */
@Component
public class Arithmetic {

    @Value("#{19 + 1}") // 20
    public double add;

    @Value("#{'String1 ' + 'String2'}") // "String1 string2"
    public String addString;

    @Value("#{20 -1}") // 19
    public double subtract;

    @Value("#{10 * 2}") // 20
    public double multiply;

    @Value("#{36 / 2}") // 19
    public double divide;

    @Value("#{36 div 2}") // 18, the same as for / operator
    public double divideAlphabetic;

    @Value("#{37 % 10}") // 7
    public double modulo;

    @Value("#{37 mod 10}") // 7, the same as for % operator
    public double moduloAlphabetic;

    @Value("#{2 ^ 9}") // 512
    public double powerOf;

    @Value("#{(2 + 2) * 2 + 9}") // 17
    public double brackets;

    @Value("${spring.new-name}") // HelloWorld!
    public String newName; // check application.properties

}
