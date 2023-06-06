package org.khasanof.webfluxserversideevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class WebFluxServerSideEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxServerSideEventApplication.class, args);
    }

}

@RestController
class SSEController {

    @GetMapping(value = "/server-event-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stringFluxEventStream() {
        return Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
                .map(m -> "Flux - " + LocalDateTime.now());
    }

    @GetMapping(value = "/server-json-value", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> stringFluxTextPlain() {
        return Flux.interval(Duration.of(3, ChronoUnit.SECONDS))
                .map(m -> "Flux - " + LocalDateTime.now());
    }

    @GetMapping(value = "/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.of(2, ChronoUnit.SECONDS))
                .map(se -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(se))
                        .event("periodic-event")
                        .data("SSE - " + LocalDateTime.now())
                        .build());
    }



}
