package org.khasanof.websocket.server.handler;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.websocket.server.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.stereotype.Controller;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server.handler
 * @since 9/14/2023 3:52 PM
 */
@Slf4j
@Controller
public class SimpleExceptionHandler {

    @MessageExceptionHandler
    public MessageDTO handleException(RuntimeException exception) {
        // ...
        return new MessageDTO();
    }

}
