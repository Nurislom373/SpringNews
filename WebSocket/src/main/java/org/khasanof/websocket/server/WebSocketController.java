package org.khasanof.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server
 * @since 9/10/2023 10:52 AM
 */
@Slf4j
@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageDTO sendTo(MessageDTO dto) {
        log.info("message mapping : {}", dto);
        return dto;
    }

}
