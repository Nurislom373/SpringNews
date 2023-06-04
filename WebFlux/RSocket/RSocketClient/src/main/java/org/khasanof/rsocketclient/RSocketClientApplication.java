package org.khasanof.rsocketclient;

import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.util.retry.Retry;

import java.time.Duration;

@SpringBootApplication
public class RSocketClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RSocketClientApplication.class, args);
    }

    @Bean
    public RSocketRequester rSocketRequester() {
        RSocketRequester.Builder builder = RSocketRequester.builder();
        return builder.rsocketConnector(
                        rSocketRequester -> rSocketRequester.reconnect(
                                Retry.fixedDelay(2, Duration.ofSeconds(2)))
                ).dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .tcp("localhost", 7000);
    }

}


@RestController
class VMController {

    private final RSocketRequester rSocketRequester;

    VMController(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @GetMapping(value = "/uuid-list/{count}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Publisher<String> uuidList(@PathVariable String count) {
        return rSocketRequester
                .route("uuid-list")
                .data(count)
                .retrieveFlux(String.class);
    }

}
