package org.khasanof.websocket;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.websocket.dto.JsonRpcResponse;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * @author Nurislom
 * @see org.khasanof.websocket
 * @since 8/31/2023 11:27 AM
 */
@Slf4j
@Component
public class SimpleStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return JsonRpcResponse.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Got a new message {}", payload);
    }

}
