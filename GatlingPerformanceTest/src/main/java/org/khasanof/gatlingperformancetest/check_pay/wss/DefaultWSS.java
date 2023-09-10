package org.khasanof.gatlingperformancetest.check_pay.wss;

import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.client.ahc
 * @since 9/2/2023 9:43 PM
 */
public class DefaultWSS extends AbstractWSS {

    private final NettyWebSocket webSocket;

    public DefaultWSS(NettyWebSocket webSocket) {
        this.webSocket = webSocket;
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

    @Override
    public CompletableFuture<Map<String, Object>> getMsgResponseToMap(String msgKey) {
        Assert.notNull(msgKey, "msgKey must not be null!");
        return completable.getResponseMsg(msgKey)
                .thenApply(messageConverter::convertToMap);
    }

    @Override
    public <T> CompletableFuture<T> getMsgResponseToType(String msgKey, Class<T> clazz) {
        Assert.notNull(msgKey, "msgKey must not be null!");
        Assert.notNull(clazz, "clazz must not be null!");
        return completable.getResponseMsg(msgKey)
                .thenApply(msg -> messageConverter.convertToType(msg, clazz));
    }

}
