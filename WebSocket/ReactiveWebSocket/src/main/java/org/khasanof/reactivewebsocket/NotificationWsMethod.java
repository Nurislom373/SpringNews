package org.khasanof.reactivewebsocket;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.reactivewebsocket.context.ReactiveWebSocketSessionContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.nio.charset.StandardCharsets;

/**
 * @author Nurislom
 * @see uz.devops.gateway.web.websocket.method
 * @since 5/29/2024 5:46 PM
 */
@Slf4j
@Component
public class NotificationWsMethod {

    private final DataBufferFactory bufferFactory = new DefaultDataBufferFactory();
    private final ReactiveWebSocketSessionContext sessionContext;

    public NotificationWsMethod(ReactiveWebSocketSessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public void executeV1() {
        Flux<Void> sendMessagesFlux = sessionContext
            .getFluxSessions()
            .filter(WebSocketSession::isOpen) // Ensure the session is open
            .flatMap(webSession -> {
                WebSocketMessage textMessage = webSession.textMessage("Hello World");
                return webSession.send(Mono.just(textMessage))
                    .doOnError(error -> System.err.println("Error sending message: " + error))
                    .doOnSuccess(aVoid -> System.out.println("Message sent to session: " + webSession.getId()));
            });

        // Subscribe to the flux to initiate the sending process
        sendMessagesFlux.subscribe(
            null,
            error -> System.err.println("Error in sendMessagesFlux: " + error),
            () -> System.out.println("Completed sending messages to all sessions")
        );
    }

    public void executeV2() {
        byte[] bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = this.bufferFactory.wrap(bytes);

        Sinks.Many<WebSocketMessage> sink = sessionContext.getSink();
        sink.tryEmitNext(new WebSocketMessage(WebSocketMessage.Type.TEXT, buffer));
    }
}
