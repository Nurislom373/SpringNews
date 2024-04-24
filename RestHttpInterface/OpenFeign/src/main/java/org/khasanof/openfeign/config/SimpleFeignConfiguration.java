package org.khasanof.openfeign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.openfeign.config
 * @since 4/24/2024 10:02 AM
 */
@Configuration
public class SimpleFeignConfiguration {

    @Bean
    public Logger.Level contract() {
        return Logger.Level.FULL;
    }
}
