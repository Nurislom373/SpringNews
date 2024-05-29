package org.khasanof.reactivewebsocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.reactivewebsocket.context.ReactiveWebSocketSessionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.reactivewebsocket.context
 * @since 5/29/2024 12:47 PM
 */
@Slf4j
@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private final ReactiveWebSocketSessionContext sessionContext;

    public ReactiveWebSocketHandler(ReactiveWebSocketSessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     *
     * @param session
     * @return
     */
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        sessionContext.addSession(session);

        return session.send(sessionContext.getSink().asFlux()
            .map(message -> session.textMessage(message.getPayloadAsText())));
    }
}
