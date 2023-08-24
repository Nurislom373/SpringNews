package org.khasanof.springaop.api;

import org.khasanof.springaop.pointcut.Log;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.springaop.api
 * @since 8/23/2023 11:37 AM
 */
@Component(PointcutRegister.NAME)
public class PointcutRegister implements Pointcut, ClassFilter, MethodMatcher {

    public static final String NAME = "pointcutRegister";
    private final ApplicationContext applicationContext;

    public PointcutRegister(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public ClassFilter getClassFilter() {
        return applicationContext.getBean(PointcutRegister.class);
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return applicationContext.getBean(PointcutRegister.class);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return true;
    }

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
