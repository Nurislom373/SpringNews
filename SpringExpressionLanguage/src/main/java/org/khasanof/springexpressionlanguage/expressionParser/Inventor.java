package org.khasanof.springexpressionlanguage.expressionParser;

import lombok.*;

import java.util.Date;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/15/2023
 * <br/>
 * Time: 7:45 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.ExpressionParser
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Inventor {
    private String name;
    private Date birthday;
    private String nationality;

    public Inventor(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
}
