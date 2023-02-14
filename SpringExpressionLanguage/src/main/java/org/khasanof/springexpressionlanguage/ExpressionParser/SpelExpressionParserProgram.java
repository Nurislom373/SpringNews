package org.khasanof.springexpressionlanguage.ExpressionParser;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.springexpressionlanguage.xmlExample.Car;
import org.springframework.boot.CommandLineRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 10:22 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.ExpressionParser
 */
@Component
@Slf4j
public class SpelExpressionParserProgram implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        stringExpression();
        concatExpression();
        lengthExpression();
        standardEvaluateExpression();
        secondStandardEvaluateExpression();
    }

    void stringExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Any String'");

        String value = (String) expression.getValue();
        log.info("ExpressionParser value - {}", value);
    }

    void concatExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello World'.concat('!')");

        String value = (String) expression.getValue();
        log.info("ExpressionParser value - {}", value);
    }

    void lengthExpression() {
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("'Any String'.length()");

        Integer value = (Integer) expression.getValue();
        log.info("ExpressionParser value - {}", value);
    }

    void standardEvaluateExpression() {
        Car car = new Car();
        car.setMake("Good manufacturer");
        car.setModel(5);
        car.setHorsePower(1000);

        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("model");

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext(car);
        Integer value = (Integer) expression.getValue(evaluationContext);
        log.info("ExpressionParser value - {}", value);
    }

    void secondStandardEvaluateExpression() {
        Car car = new Car();
        car.setMake("Good manufacturer");
        car.setModel(5);
        car.setHorsePower(1000);

        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("model > 4");

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext(car);
        Boolean value = expression.getValue(evaluationContext, Boolean.class);
        log.info("ExpressionParser value - {}", value);
    }
}
