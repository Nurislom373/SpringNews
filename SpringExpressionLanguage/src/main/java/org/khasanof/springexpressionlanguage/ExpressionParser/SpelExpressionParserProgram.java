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
        relationalExpression();
        mathematicalExpression();
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

    /*
        The relational operators; equal, not equal, less than, less than or equal, greater than,
        and greater than or equal are supported using standard operator notation.

        The logical operators that are supported are and, or, and not. Their use is demonstrated below.
     */
    void relationalExpression() {
        SpelExpressionParser expressionParser = new SpelExpressionParser();

        // Evaluates to true
        Boolean value1 = expressionParser.parseExpression("5 > 4").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value1);

        // evaluates to false
        Boolean value2 = expressionParser.parseExpression("2 < -5.0").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value2);

        // evaluates to true
        Boolean value3 = expressionParser.parseExpression("'black' < 'block'").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value3);

        // evaluates to false
        Boolean value4 = expressionParser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value4);

        // evaluates to true
        Boolean value5 = expressionParser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value5);

        //evaluates to false
        Boolean value6 = expressionParser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value6);

        // -- AND --

        // evaluates to false
        Boolean value7 = expressionParser.parseExpression("true and false").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value7);

        // -- OR --

        // evaluates to true
        Boolean value8 = expressionParser.parseExpression("true or false").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value8);

        // -- NOT --

        // evaluates to false
        Boolean value9 = expressionParser.parseExpression("!true").getValue(Boolean.class);
        log.info("ExpressionParser value - {}", value9);
    }

    /*
        The addition operator can be used on numbers, strings and dates. Subtraction can be used on numbers and dates.
         Multiplication and division can be used only on numbers. Other mathematical operators supported are modulus
         (%) and exponential power (^). Standard operator precedence is enforced. These operators are demonstrated below.
     */
    void mathematicalExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();

        // Addition
        Integer two = parser.parseExpression("1 + 1").getValue(Integer.class); // 2

        String testString =
                parser.parseExpression("'test' + ' ' + 'string'").getValue(String.class);  // 'test string'

        // Subtraction
        Integer four = parser.parseExpression("1 - -3").getValue(Integer.class); // 4

        Double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class); // -9000

        // Multiplication
        Integer six = parser.parseExpression("-2 * -3").getValue(Integer.class); // 6

        Double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class); // 24.0

        // Division
        Integer minusTwo = parser.parseExpression("6 / -3").getValue(Integer.class); // -2

        Double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class); // 1.0

        // Modulus
        Integer three = parser.parseExpression("7 % 4").getValue(Integer.class); // 3

        Integer secondOne = parser.parseExpression("8 / 5 % 2").getValue(Integer.class); // 1

        // Operator precedence
        Integer minusTwentyOne = parser.parseExpression("1 + 2 - 3 * 8").getValue(Integer.class); // -21
    }
}
