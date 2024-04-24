package org.khasanof.openfeign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign
 * @since 4/24/2024 11:10 AM
 */
@Slf4j
//@Component
public class FeignTodoRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("REQUEST to uri : {}", requestTemplate.url());
    }
}
