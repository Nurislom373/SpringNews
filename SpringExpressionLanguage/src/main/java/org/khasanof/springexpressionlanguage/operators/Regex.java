package org.khasanof.springexpressionlanguage.operators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:48 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.operators
 * <br/>
 * <br/>
 * We can use the matches operator to check whether or not a string matches a given regular expression
 */
@Component
public class Regex {

    @Value("#{'100' matches '\\d+' }") // true
    private boolean validNumericStringResult;

    @Value("#{'100fghdjf' matches '\\d+' }") // false
    private boolean invalidNumericStringResult;

    @Value("#{'valid alphabetic string' matches '[a-zA-Z\\s]+' }") // true
    private boolean validAlphabeticStringResult;

    @Value("#{'invalid alphabetic string #$1' matches '[a-zA-Z\\s]+' }") // false
    private boolean invalidAlphabeticStringResult;

//    @Value("#{someBean.someValue matches '\\d+'}") // true if someValue contains only digits
    private boolean validNumericValue;
}
