package org.khasanof.springjdbctemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/22/2023
 * <br/>
 * Time: 5:50 PM
 * <br/>
 * Package: org.khasanof.springjdbctemplate.config
 */
@Configuration
@ComponentScan(value = "org.khasanof.springjdbctemplate")
public class JdbcTemplateConfig {

    // Spring Config Example
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/jdbc_temp");
        dataSource.setUsername("postgres");
        dataSource.setPassword("2004");
        return dataSource;
    }
}
