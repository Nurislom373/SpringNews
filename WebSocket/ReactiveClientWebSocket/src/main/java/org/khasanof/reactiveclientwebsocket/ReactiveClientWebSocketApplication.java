package org.khasanof.reactiveclientwebsocket;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@SpringBootApplication
public class ReactiveClientWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveClientWebSocketApplication.class, args);
    }

}

@Component
class Runner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
                        URI.create("ws://localhost:8080/reactive"),
                        session -> session.send(
                                        Mono.just(session.textMessage("event-spring-reactive-client-websocket")))
                                .thenMany(session.receive()
                                        .map(WebSocketMessage::getPayloadAsText)
                                        .log())
                                .then())
                .block(Duration.ofSeconds(10L));
    }

}
