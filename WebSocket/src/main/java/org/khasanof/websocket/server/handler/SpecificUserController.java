package org.khasanof.websocket.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.websocket.server.MessageDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

import java.security.Principal;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server.handler
 * @since 9/14/2023 4:37 PM
 */
@Slf4j
@Controller
public class SpecificUserController {

    private final SimpMessagingTemplate messagingTemplate;

    public SpecificUserController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/secured/room")
    public void sendToUser(@Payload TextMessage message, Principal user, @Header("simpSessionId") String sessionId) {
        MessageDTO dto = new MessageDTO("Abdulloh", "Hello World. pon");
        messagingTemplate.convertAndSendToUser(
                sessionId, "/secured/user/queue/specific-user", dto);
    }

}
