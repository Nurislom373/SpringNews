package org.khasanof.websocket.ahc;

import org.asynchttpclient.ws.WebSocketListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.ahc
 * @since 9/2/2023 9:07 PM
 */
public interface PlusListener {

    WebSocketUpgradeHandler getBuildUpgradeHandler();

    String sendMessage(String message);

    CompletableFuture<String> getMsgResponse(String msgKey);

    static WebSocketUpgradeHandler buildUpgradeHandler(WebSocketListener listener) {
        WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();
        return builder.addWebSocketListener(listener).build();
    }

}
