package org.khasanof.springexpressionlanguage.expressionParser;

import org.springframework.expression.ParserContext;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/15/2023
 * <br/>
 * Time: 9:06 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.expressionParser
 */
public class TemplateParserContext implements ParserContext {

    @Override
    public boolean isTemplate() {
        return true;
    }

    @Override
    public String getExpressionPrefix() {
        return "#{";
    }

    @Override
    public String getExpressionSuffix() {
        return "}";
    }
}
