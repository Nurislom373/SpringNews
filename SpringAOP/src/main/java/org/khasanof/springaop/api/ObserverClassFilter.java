package org.khasanof.springaop.api;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ClassFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see org.khasanof.springaop.api
 * @since 8/23/2023 11:39 AM
 */
@Aspect
@Component
public class ObserverClassFilter implements ClassFilter {
    @Override
    public boolean matches(Class<?> clazz) {
        return clazz.isAnnotationPresent(Service.class);
    }
}
