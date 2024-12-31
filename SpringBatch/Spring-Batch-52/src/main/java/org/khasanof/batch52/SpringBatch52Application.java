package org.khasanof.batch52;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBatch52Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch52Application.class, args);
    }

}
