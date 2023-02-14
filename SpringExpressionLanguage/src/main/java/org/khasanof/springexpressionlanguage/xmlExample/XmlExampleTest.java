package org.khasanof.springexpressionlanguage.xmlExample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 10:15 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.xmlExample
 */
public class XmlExampleTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        SpelOperators spelOperators = (SpelOperators) context.getBean("spelOperators");

        System.out.println("spelOperators = " + spelOperators);
    }
}
