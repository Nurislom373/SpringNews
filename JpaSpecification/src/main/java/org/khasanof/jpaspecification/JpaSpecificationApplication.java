package org.khasanof.jpaspecification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class JpaSpecificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSpecificationApplication.class, args);
    }

}
