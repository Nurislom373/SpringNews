package org.khasanof.ratelimitingwithspring.config;

import org.khasanof.ratelimitingwithspring.RateLimitInterceptor;
import org.khasanof.ratelimitingwithspring.RateLimitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    @Lazy
    private RateLimitInterceptor interceptor;

    @Autowired
    @Lazy
    private RateLimitingService service;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urls = new ArrayList<>(service.getAllUrls());
        registry.addInterceptor(interceptor)
                .addPathPatterns(urls);
    }
}
