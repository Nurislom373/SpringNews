package org.khasanof.rsocketclient;

import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.core.codec.StringDecoder;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
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

}

@Configuration
class RSocketConfiguration {

    @Bean
    public RSocketStrategies rsocketStrategies() {
        return RSocketStrategies.builder()
                .decoder(StringDecoder.textPlainOnly())
                .encoder(CharSequenceEncoder.allMimeTypes())
                .dataBufferFactory(new DefaultDataBufferFactory(true))
                .build();
    }

    @Bean
    public RSocketRequester rSocketRequester() {
        RSocketRequester.Builder builder = RSocketRequester.builder();
        return builder.rsocketStrategies(rsocketStrategies())
                .rsocketConnector(rSocketRequester -> rSocketRequester.reconnect(
                        Retry.fixedDelay(2, Duration.ofSeconds(2)))
        ).tcp("localhost", 7000);
    }
}

@RestController
class VMController {

    private final RSocketRequester rSocketRequester;

    VMController(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @GetMapping(value = "/uuid-list/{count}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Publisher<String> uuidList(@PathVariable Integer count) {

        // java.lang.IllegalArgumentException: No encoder for java.lang.Integer exception fixed
        RSocketStrategies strategies = RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .build();

        RSocketRequester requester = RSocketRequester.builder()
                .rsocketStrategies(strategies)
                .tcp("localhost", 7000);

        return requester
                .route("uuid-list")
                .data(count)
                .retrieveFlux(String.class);
    }

    @GetMapping(value = "/interval", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Publisher<String> interval() {
        return rSocketRequester
                .route("intervalObjectPush")
                .retrieveFlux(String.class);
    }

}
