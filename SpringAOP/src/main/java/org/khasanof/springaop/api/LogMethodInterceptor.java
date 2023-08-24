package org.khasanof.springaop.api;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Nurislom
 * @see org.khasanof.springaop.api
 * @since 8/23/2023 11:41 AM
 */
@Slf4j
public class LogMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("execute method name : {}", invocation.getMethod().getName());
        return invocation.proceed();
    }
}
