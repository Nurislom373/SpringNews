package org.khasanof.websockethandler;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@SpringBootApplication
public class WebSocketHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketHandlerApplication.class, args);
    }

}

@Configuration
@EnableWebSocket
class Config implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(simpleHandler(), "/simple");
    }

    @Bean
    public SimpleHandler simpleHandler() {
        return new SimpleHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}

@Slf4j
class SimpleHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Successfully Connected : {}", session);
        session.sendMessage(new TextMessage("Connected!"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("received message : {}", message);
        session.sendMessage(new TextMessage("Send Simple Message"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.warn("exception thrown : {}", exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        session.close(CloseStatus.NORMAL);
        log.info("connection closed!");
    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }
}

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class MessageDTO {

    private String form;
    private String text;

}
