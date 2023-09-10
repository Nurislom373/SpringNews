package org.khasanof.websocket.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.websocket.server.MessageDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server.handler
 * @since 9/10/2023 3:35 PM
 */
@Slf4j
public class SimpleHandler extends TextWebSocketHandler {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SimpleHandler(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Successfully Connected : {}", session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("received message : {}", message);
        sendReply(objectMapper.convertValue(message.getPayload(), MessageDTO.class));
    }

    private void sendReply(MessageDTO dto) {
        dto.setText("I'm simple Handler " + dto.getForm());
        simpMessagingTemplate.convertAndSend("/topic/messages", dto);
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
