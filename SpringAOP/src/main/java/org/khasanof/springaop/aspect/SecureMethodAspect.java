package org.khasanof.springaop.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 3:44 PM
 * <br/>
 * Package: org.khasanof.springaop.aspect
 */
@Aspect
@Slf4j
public class SecureMethodAspect {

    @Pointcut("@annotation(secured)")
    public void callAt(Secured secured) {
    }

    @Around("callAt(secured)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Secured secured) throws Throwable {
        if (secured.isLocked()) {
            log.info(proceedingJoinPoint.getSignature().toLongString() + "is locked");
            return null;
        } else {
            return proceedingJoinPoint.proceed();
        }
    }
}
