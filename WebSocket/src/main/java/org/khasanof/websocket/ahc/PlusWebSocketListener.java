package org.khasanof.websocket.ahc;

import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.ahc
 * @since 9/2/2023 8:56 PM
 */
@Slf4j
public class PlusWebSocketListener implements WebSocketListener {

    private final PlusWebSocketCompletable nettyWebSocketUniquer;

    public PlusWebSocketListener(PlusWebSocketCompletable nettyWebSocketUniquer) {
        this.nettyWebSocketUniquer = nettyWebSocketUniquer;
    }

    @Override
    public void onOpen(WebSocket websocket) {
        log.info("websocket connection opened!");
    }

    @Override
    public void onClose(WebSocket websocket, int code, String reason) {
        log.info("websocket connection closed!");
    }

    @Override
    public void onTextFrame(String payload, boolean finalFragment, int rsv) {
        log.info("reply received : {}", payload);
        Optional<String> optional = nettyWebSocketUniquer.getFirstAddQueue();
        if (optional.isPresent() && (Objects.nonNull(payload) && !payload.isBlank())) {
            String key = optional.get();
            nettyWebSocketUniquer.addReceivedMessage(key, payload);
        }
    }

    @Override
    public void onError(Throwable t) {
        log.warn("exception throwing! : {}", t.getMessage());
    }
}
