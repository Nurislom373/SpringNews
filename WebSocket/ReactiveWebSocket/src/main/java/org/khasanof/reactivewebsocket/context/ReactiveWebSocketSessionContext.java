package org.khasanof.reactivewebsocket.context;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.reactivewebsocket.context
 * @since 5/29/2024 5:26 PM
 */
public interface ReactiveWebSocketSessionContext {
    /**
     *
     * @param session
     */
    void addSession(WebSocketSession session);

    /**
     *
     * @return
     */
    Set<WebSocketSession> getSessions();

    /**
     *
     * @return
     */
    Flux<WebSocketSession> getFluxSessions();

    /**
     *
     * @return
     */
    Sinks.Many<WebSocketMessage> getSink();
}
