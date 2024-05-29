package org.khasanof.reactivewebsocket.handler;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.reactivewebsocket
 * @since 9/23/2023 11:20 AM
 */
@Slf4j
@Component
public class SimpleReactiveWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> socketMessageFlux = session.receive()
                .doOnNext(webSocketMessage -> log.info("receive new a message : {}",
                        webSocketMessage.getPayloadAsText())).repeat(10)
                .map(webSocketMessage -> session.textMessage(Faker.instance().lorem().paragraph()));
        return session.send(socketMessageFlux);
    }
}
