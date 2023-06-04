package org.khasanof.rsocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootApplication
public class RSocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RSocketServerApplication.class, args);
    }

}

@Controller
class SimulationController {

    @MessageMapping("uuid-list")
    public Flux<String> simulateString(Integer count) {
        return Flux.range(0, count)
                .map(m -> UUID.randomUUID().toString());
    }

}