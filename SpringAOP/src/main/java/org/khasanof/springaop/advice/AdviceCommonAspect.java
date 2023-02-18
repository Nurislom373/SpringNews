package org.khasanof.springaop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/18/2023
 * <br/>
 * Time: 10:01 PM
 * <br/>
 * Package: org.khasanof.springaop.advice
 */
@Component
@Aspect
public class AdviceCommonAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(* org.khasanof.springaop.advice.AdviceService.checkout())")
    public void doCheckout() {
        System.out.println("Before called checkout method!");
    }

    @After("execution(* org.khasanof.springaop.advice.AdviceService.checkout())")
    public void afterDoCheckout() {
        System.out.println("After called checkout method!");
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void repositoryClassMethods() {};

    @Around("repositoryClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        logger.info("Execution of " + methodName + " took " +
                TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }

}
