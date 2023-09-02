package org.khasanof.websocket.ahc;

import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.ahc
 * @since 9/2/2023 9:43 PM
 */
public class SimplePlusListener implements PlusListener {

    private final NettyWebSocket webSocket;
    private final PlusWebSocketCompletable completable = new PlusWebSocketCompletable();
    private final PlusWebSocketListener listener = new PlusWebSocketListener(completable);

    public SimplePlusListener(NettyWebSocket webSocket) {
        this.webSocket = webSocket;
    }

    @Override
    public WebSocketUpgradeHandler getBuildUpgradeHandler() {
        WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();
        return builder.addWebSocketListener(listener).build();
    }

    @Override
    public String sendMessage(String message) {
        Assert.notNull(message, "message must not be null!");
        return completable.sendMessage(this.webSocket, message);
    }

    @Override
    public CompletableFuture<String> getMsgResponse(String msgKey) {
        Assert.notNull(msgKey, "msgKey must not be null!");
        return completable.getResponseMsg(msgKey);
    }

}
