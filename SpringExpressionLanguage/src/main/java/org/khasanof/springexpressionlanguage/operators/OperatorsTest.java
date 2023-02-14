package org.khasanof.springexpressionlanguage.operators;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.springexpressionlanguage.operators.Arithmetic;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 5:38 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage
 */
@Component
@Slf4j
public class OperatorsTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Arithmetic arithmetic = applicationContext.getBean(Arithmetic.class);

        log.warn("SpEL out newName - {}", arithmetic.newName);
        log.warn("SpEL out addString - {}", arithmetic.addString);
    }
}
