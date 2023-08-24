package org.khasanof.springaop.config;

import org.khasanof.springaop.api.LogMethodInterceptor;
import org.khasanof.springaop.pointcut.TestService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Nurislom
 * @see org.khasanof.springaop.config
 * @since 8/23/2023 12:19 PM
 */
@Configuration
public class LogMethodConfig {

    @Bean
    public LogMethodInterceptor logMethodInterceptor() {
        return new LogMethodInterceptor();
    }

    @Bean
    public BeanNameAutoProxyCreator globalInterceptorAutoProxy() {
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setInterceptorNames("logMethodInterceptor");
        proxyCreator.setBeanNames("*"); // Apply to all beans
        return proxyCreator;
    }

}
