package org.khasanof.websocketclient;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompClientSupport;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.websocketclient
 * @since 9/19/2023 2:06 PM
 */
@Slf4j
@Component
public class CustomWsHandler implements ApplicationRunner {

    private static final String URL_SIMPLE = "ws://localhost:8081/simple";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        connect();
    }

    @SneakyThrows
    private void connect() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        CompletableFuture<WebSocketSession> future = webSocketClient.execute(textWebSocketHandler(), URL_SIMPLE);
        WebSocketSession webSocketSession = future.get();
        webSocketSession.sendMessage(new TextMessage("Hi Ken"));


        Thread.sleep(3000);
        webSocketSession.close(CloseStatus.NORMAL);
//        StompSessionHandler stompSessionHandler = new SimpleStompSessionHandler();
//        StompSession stompSession = stompClient.connectAsync(URL_SIMPLE, stompSessionHandler).get();
//        StompHeaders headers = new StompHeaders();
//        stompSession.send(headers, "Hi Ken");
//
//        Thread.sleep(3000);
//        stompSession.disconnect();
    }

    private TextWebSocketHandler textWebSocketHandler() {
        return new TextWebSocketHandler() {

            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                log.info("Successfully Connected WebSocket 😎, session : {}", session);
            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                log.info("I'm handle text message : {}, session : {}", new String(message.asBytes(), StandardCharsets.UTF_8), session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                log.warn("Connection Closed!, session : {}", session);
            }

        };
    }

}
