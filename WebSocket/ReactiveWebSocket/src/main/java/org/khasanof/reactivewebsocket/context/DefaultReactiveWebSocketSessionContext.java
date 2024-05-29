package org.khasanof.reactivewebsocket.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.reactivewebsocket.context
 * @since 5/29/2024 5:22 PM
 */
@Slf4j
@Service
public class DefaultReactiveWebSocketSessionContext implements ReactiveWebSocketSessionContext {

//    private final Map<String, WebSocketSession> sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final Sinks.Many<WebSocketMessage> sink = Sinks.many().multicast().onBackpressureBuffer();

    /**
     *
     * @param session
     */
    public void addSession(WebSocketSession session) {
        this.sessions.put(session.getId(), session);

        session.receive()
            .doOnNext(webSocketMessage -> log.info("receive new a message : {}", webSocketMessage.getPayloadAsText()))
            .doFinally((signal) -> sessions.remove(session.getId()))
            .subscribe();
    }

    @Override
    public Set<WebSocketSession> getSessions() {
        return new HashSet<>(this.sessions.values());
    }

    /**
     *
     * @return
     */
    @Override
    public Flux<WebSocketSession> getFluxSessions() {
        return Flux.fromIterable(this.sessions.values());
    }

    @Override
    public Sinks.Many<WebSocketMessage> getSink() {
        return sink;
    }
}
