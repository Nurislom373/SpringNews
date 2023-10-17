package org.khasanof.websocketstomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.websocketstomp
 * @since 10/1/2023 4:39 PM
 */
@Slf4j
@Component
public class SimpMessageChannel implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("I'm handle message : {}", message);
    }

}
