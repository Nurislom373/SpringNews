package org.khasanof.springaop.api;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.khasanof.springaop.pointcut.Log;
import org.springframework.aop.BeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.springaop.api
 * @since 8/23/2023 11:27 AM
 */
@Slf4j
@Aspect
@Component
public class ObserverBeforeAdvice implements BeforeAdvice {

    private int count;

    void before(Method m, Object[] args, Object target) throws Throwable {
        if (m.isAnnotationPresent(Log.class)) {
            log.info("enter observer!");
            ++count;
        } else {
            log.warn("is not observer method!");
        }
    }

    public int getCount() {
        return count;
    }

}
