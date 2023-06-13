package org.khasanof.rsocketserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
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
                .delayElements(Duration.ofSeconds(1))
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

    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(@DestinationVariable("name") String name, Mono<String> greetingMono) {
        return greetingMono
                .doOnNext(greeting ->
                        log.info("Received a greeting from {} : {}", name, greeting))
                .map(greeting -> "Hello to you, too, " + name);
    }

    @MessageMapping("stock/{symbol}")
    public Flux<StockQuote> getStockPrice(@DestinationVariable("symbol") String symbol) {
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(i -> {
                    BigDecimal price = BigDecimal.valueOf(Math.random() * 10);
                    return new StockQuote(symbol, price, Instant.now());
                });
    }

    @MessageMapping("alert")
    public Mono<Void> setAlert(Mono<Alert> alertMono) {
        return alertMono
                .doOnNext(alert ->
                        log.info("{} alert ordered by {} at {}",
                                alert.getLevel(),
                                alert.getOrderedBy(),
                                alert.getOrderedAt())
                )
                .thenEmpty(Mono.empty());
    }

}

@Data
@AllArgsConstructor
class StockQuote {
    private String symbol;
    private BigDecimal price;
    private Instant timestamp;
}

@Data
@AllArgsConstructor
class Alert {
    private Level level;
    private String orderedBy;
    private Instant orderedAt;
    public enum Level {
        YELLOW, ORANGE, RED, BLACK
    }
}