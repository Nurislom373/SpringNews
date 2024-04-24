package org.khasanof.openfeign.interceptor;

import feign.InvocationContext;
import feign.Response;
import feign.ResponseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.interceptor
 * @since 4/24/2024 11:13 AM
 */
@Slf4j
//@Component
public class FeignTodoResponseInterceptor implements ResponseInterceptor {

    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        Response response = invocationContext.response();
        log.info("RESPONSE to : {}", response);
        return response;
    }
}
