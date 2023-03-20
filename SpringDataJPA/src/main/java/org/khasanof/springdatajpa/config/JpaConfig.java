package org.khasanof.springdatajpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 4:31 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.config
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.khasanof.springdatajpa.repository")
public class JpaConfig {
}
