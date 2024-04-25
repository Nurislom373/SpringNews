package org.khasanof;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/25/2024 10:24 AM
 */
@Component
public class SpringValidatorInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;
    private final SpringValidatorRegistry registry;

    public SpringValidatorInterceptor(ObjectMapper objectMapper, SpringValidatorRegistry registry) {
        this.objectMapper = objectMapper;
        this.registry = registry;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = readRequestObject(request.getReader(), (HandlerMethod) handler);
        Set<Validator> validators = registry.validators();
        validators.forEach(validator -> {

        });
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @SneakyThrows
    private Object readRequestObject(BufferedReader bufferedReader, HandlerMethod handlerMethod) {
        return objectMapper.readValue(bufferedReader, getParameterType(handlerMethod));
    }

    private Class<?> getParameterType(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getParameterTypes()[0];
    }
}
