package org.khasanof.springexpressionlanguage.expressionParser;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/15/2023
 * <br/>
 * Time: 9:02 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.expressionParser
 */
public class StringUtils {

    public static String reverseString(String input) {
        StringBuilder backwards = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            backwards.append(input.charAt(input.length() - 1 - i));
        return backwards.toString();
    }
}
