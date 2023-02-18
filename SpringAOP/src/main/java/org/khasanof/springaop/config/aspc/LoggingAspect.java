package org.khasanof.springaop.config.aspc;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/18/2023
 * <br/>
 * Time: 10:57 AM
 * <br/>
 * Package: org.khasanof.springaop.config.aspc
 */
@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(Log)")
    public void logPointcut(){
    }

    @Before("logPointcut()")
    public void logAllMethodCallsAdvice(){
        System.out.println("In Aspect");
    }

    @Pointcut("execution(public void org.khasanof.springaop.config.aspc.TestService.observer())")
    public void logPointcutWithExecution() {}

    @Before("logPointcutWithExecution()")
    public void logMethodCallsWithExecutionAdvice() {
        System.out.println("In Aspect from execution");
    }
}
