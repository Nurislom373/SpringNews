package org.khasanof.rsocketserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@SpringBootApplication
public class RSocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RSocketServerApplication.class, args);
    }

}

@Slf4j
@Controller
class SimulationController {

    @MessageMapping("uuid-list")
    public Flux<String> simulateString(@Payload Integer count) {
        return Flux.range(0, count)
                .map(m -> UUID.randomUUID().toString());
    }

    @MessageMapping("intervalObjectPush")
    public Flux<String> intervalObjectPush() {
        return Flux.interval(Duration.of(3, ChronoUnit.SECONDS))
                .map(m -> LocalDateTime.now().toString());
    }

    @MessageMapping("req-resp/{name}")
    public Mono<String> greeting(@DestinationVariable String name) {
        return Mono.just(name)
                .doOnNext(next -> log.info("Received a greeting from {}", next))
                .map(greeting -> "Hello " + greeting + "!");
    }

}