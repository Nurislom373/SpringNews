package org.khasanof.websocket.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server.handler
 * @since 9/14/2023 3:53 PM
 */
@Slf4j
@Controller
public class SimpleMessagingTempController {

    private SimpMessagingTemplate template;

    @Autowired
    public SimpleMessagingTempController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/greetings")
    public void greet(String greeting) {
        String text = "[" + System.currentTimeMillis() + "]:" + greeting;
        this.template.convertAndSend("/topic/greetings", text);
    }

}
