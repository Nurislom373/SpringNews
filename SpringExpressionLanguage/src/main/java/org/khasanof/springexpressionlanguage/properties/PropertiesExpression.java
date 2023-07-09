package org.khasanof.springexpressionlanguage.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/Nurislom373">Nurislom</a>
 * @see org.khasanof.springexpressionlanguage.properties
 * @since 24.06.2023 23:25
 */
@Component
public class PropertiesExpression implements CommandLineRunner {

    @Value("${spring.method:false}")
    private Boolean springMethod;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("springMethod = " + springMethod);
    }
}
