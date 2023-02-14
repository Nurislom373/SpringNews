package org.khasanof.springexpressionlanguage.operators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:45 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.operators
 * <br/>
 * <br/>
 * We use conditional operators for injecting different values depending on some condition
 * We use the ternary operator for performing compact if-then-else conditional logic inside the expression.
 * In this example, we're trying to check if there was true or not.
 */
@Component
public class Conditional {

    @Value("#{2 > 1 ? 'a' : 'b'}") // "a"
    private String ternary;

    /*
        Another common use for the ternary operator is to check if some variable
        is null and then return the variable value or a default
     */
//    @Value("#{someBean.someProperty != null ? someBean.someProperty : 'default'}")
    private String ternaryNull;

//    @Value("#{someBean.someProperty ?: 'default'}") // Will inject provided string if someProperty is null
    private String elvis;

}
