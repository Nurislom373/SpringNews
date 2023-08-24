package org.khasanof.springaop.api;

import org.aspectj.lang.annotation.Aspect;
import org.khasanof.springaop.pointcut.Log;
import org.springframework.aop.MethodMatcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.springaop.api
 * @since 8/23/2023 11:26 AM
 */
@Aspect
@Component
public class ObserverMethodMatcher implements MethodMatcher {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.isAnnotationPresent(Log.class);
    }

    @Override
    public boolean isRuntime() {
        return true;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return method.isAnnotationPresent(Log.class);
    }
}
