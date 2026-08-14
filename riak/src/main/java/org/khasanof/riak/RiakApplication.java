package org.khasanof.riak;

import org.khasanof.riak.config.RiakConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RiakConfig.class})
public class RiakApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiakApplication.class, args);
    }

}
