package org.khasanof.springexpressionlanguage.operators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:44 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.operators
 * <br/>
 * <br/>
 * SpEL also supports all basic logical operations
 */
@Component
public class Logical {

    @Value("#{250 > 200 && 200 < 4000}") // true
    private boolean and;

    @Value("#{250 > 200 and 200 < 4000}") // true
    private boolean andAlphabetic;

    @Value("#{400 > 300 || 150 < 100}") // true
    private boolean or;

    @Value("#{400 > 300 or 150 < 100}") // true
    private boolean orAlphabetic;

    @Value("#{!true}") // false
    private boolean not;

    @Value("#{not true}") // false
    private boolean notAlphabetic;

}
