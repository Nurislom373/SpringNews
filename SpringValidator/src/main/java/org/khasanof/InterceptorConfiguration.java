package org.khasanof;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/25/2024 10:32 AM
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final SpringValidatorInterceptor interceptor;

    public InterceptorConfiguration(SpringValidatorInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
