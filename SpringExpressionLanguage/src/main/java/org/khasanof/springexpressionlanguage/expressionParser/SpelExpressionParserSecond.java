package org.khasanof.springexpressionlanguage.expressionParser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/15/2023
 * <br/>
 * Time: 7:44 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.ExpressionParser
 */
@Component
@Slf4j
public class SpelExpressionParserSecond implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        start();
        listExample();
        secondListExample();
        methodExample();
        assignmentExample();
        constructorExample();
        rootExample();
        functionExample();
        variablesExample();
    }

    private void start() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(1856, Calendar.AUGUST, 9);

        Inventor inventor = new Inventor("Nikola Tesla", calendar.getTime(), "Serbian");

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("name");
        StandardEvaluationContext context = new StandardEvaluationContext(inventor);

        String value = expression.getValue(context, String.class);
        log.info("Log Value 1 {}", value);
    }

    private void listExample() {
        Simple simple = new Simple();

        simple.booleanList.add(true);

        SpelExpressionParser expressionParser = new SpelExpressionParser();

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(simple);
        Boolean value = (Boolean) expressionParser.parseExpression("booleanList[0]")
                .getValue(standardEvaluationContext);

        log.info("Log Value 2 {}", value);
    }

    private void secondListExample() {
        SpelExpressionParser expressionParser = new SpelExpressionParser();

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        List value = (List) expressionParser.parseExpression("{1, 2, 3, 4}")
                .getValue(standardEvaluationContext);

        List listOfLists = (List) expressionParser.parseExpression("{{'a','b'},{'x','y'}}")
                .getValue(standardEvaluationContext);

        int[] numbers1 = (int[]) expressionParser.parseExpression("new int[4]")
                .getValue(standardEvaluationContext);

        // Array with initializer
        int[] numbers2 = (int[]) expressionParser.parseExpression("new int[]{1,2,3}")
                .getValue(standardEvaluationContext);

        // Multi dimensional array
        int[][] numbers3 = (int[][]) expressionParser.parseExpression("new int[4][5]")
                .getValue(standardEvaluationContext);

        log.info("Log Value 3 {}", value);
        log.info("Log Value 4 {}", listOfLists);
        log.info("Log Value 5 {}", numbers1);
        log.info("Log Value 6 {}", numbers2);
        log.info("Log Value 7 {}", numbers3);
    }

    private void methodExample() {
        SpelExpressionParser expressionParser = new SpelExpressionParser();

        String value = expressionParser.parseExpression("'abc'.substring(2, 3)").getValue(String.class);
        log.info("Log Value 8 {}", value);

//        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
//        // evaluates to true
//        boolean isMember = expressionParser.parseExpression("isMember('Mihajlo Pupin')")
//                .getValue(standardEvaluationContext, Boolean.class);
    }

    private void assignmentExample() {
        Inventor inventor = new Inventor();

        SpelExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext(inventor);

        parser.parseExpression("name").setValue(context, "Nurislom");

        System.out.println(inventor);
    }

    private void constructorExample() {
        SpelExpressionParser parser = new SpelExpressionParser();

        Inventor value = parser.parseExpression("new org.khasanof.springexpressionlanguage.ExpressionParser.Inventor('Jeck', 'German')")
                .getValue(Inventor.class);
        System.out.println("value = " + value);
    }

    private void rootExample() {
        // create an array of integers
        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        // create parser and set variable 'primes' as the array of integers
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("primes", primes);

        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTen =
                (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);
        System.out.println("primesGreaterThanTen = " + primesGreaterThanTen);
    }

    private void functionExample() throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        context.registerFunction("reverseString",
                StringUtils.class.getDeclaredMethod("reverseString",
                        new Class[]{String.class}));

        String helloWorldReversed =
                parser.parseExpression("#reverseString('hello')").getValue(context, String.class);
        System.out.println("helloWorldReversed = " + helloWorldReversed);
    }

    private void variablesExample() {
        SpelExpressionParser parser = new SpelExpressionParser();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        StandardEvaluationContext context = new StandardEvaluationContext(tesla);
        context.setVariable("newName", "Mike Tesla");

        parser.parseExpression("Name = #newName").getValue(context);

        System.out.println(tesla.getName()); // "Mike Tesla"
    }

    private void expressionRandomExample() {
        SpelExpressionParser parser = new SpelExpressionParser();

        String randomPhrase =
                parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                        new TemplateParserContext()).getValue(String.class);

        System.out.println("randomPhrase = " + randomPhrase);
    }


}
