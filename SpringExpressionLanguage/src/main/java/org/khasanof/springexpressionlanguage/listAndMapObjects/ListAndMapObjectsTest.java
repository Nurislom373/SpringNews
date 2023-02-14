package org.khasanof.springexpressionlanguage.listAndMapObjects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 7:28 PM
 * <br/>
 * Package: org.khasanof.springexpressionlanguage.listAndMapObjects
 */
@Component
@Slf4j
public class ListAndMapObjectsTest implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WorkersHolderSPL holderSPL = applicationContext.getBean(WorkersHolderSPL.class);

        log.info("SpEL out firstWorker {}", holderSPL.firstWorker);
        log.info("SpEL out georgeSalary {}", holderSPL.georgeSalary);
        log.info("SpEL out johnSalary {}", holderSPL.johnSalary);
        log.info("SpEL out lastWorker {}", holderSPL.lastWorker);
        log.info("SpEL out susieSalary {}", holderSPL.susieSalary);
        log.info("SpEL out numberOfWorkers {}", holderSPL.numberOfWorkers);
    }
}
