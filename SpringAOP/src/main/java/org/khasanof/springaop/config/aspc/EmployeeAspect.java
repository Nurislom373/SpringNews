package org.khasanof.springaop.config.aspc;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/18/2023
 * <br/>
 * Time: 11:26 AM
 * <br/>
 * Package: org.khasanof.springaop.config.aspc
 */
@Component
@Aspect
public class EmployeeAspect {

    @Before("execution(* EmployeeManager.getEmployeeById(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("EmployeeAspect.logBefore() : " + joinPoint.getSignature().getName());
    }
}
