package org.khasanof.gatlingperformancetest;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest
 * @since 8/24/2023 6:00 PM
 */
@Configuration
public class GatlingConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

}
