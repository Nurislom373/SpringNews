package org.khasanof.websocketstomp;

import lombok.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Nurislom
 * @see org.khasanof.websocketstomp
 * @since 10/1/2023 4:43 PM
 */
@Controller
public class MessageController {

    @MessageMapping("/app/greeting")
    @SendTo("/topic/messages")
    public String handle(@Payload GreetingDTO greeting) {
        return "[" + System.currentTimeMillis() + ": " + greeting.message;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GreetingDTO {
        private String from;
        private String message;
    }

}
