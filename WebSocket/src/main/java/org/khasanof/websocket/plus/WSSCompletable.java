package org.khasanof.websocket.plus;

import org.apache.commons.lang3.RandomStringUtils;
import org.asynchttpclient.netty.ws.NettyWebSocket;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.ahc
 * @since 9/2/2023 8:39 PM
 */
public class WSSCompletable {

    private final Queue<String> receiveKeysQueue = new ConcurrentLinkedQueue<>();
    private final Map<String, CompletableFuture<String>> sendMessageIdAndReceived = new ConcurrentHashMap<>();

    public String sendMessage(NettyWebSocket webSocket, String message) {
        String key = RandomStringUtils.randomAlphanumeric(10);
        receiveKeysQueue.add(key);
        sendMessageIdAndReceived.put(key, new CompletableFuture<>());
        webSocket.sendTextFrame(message);
        return key;
    }

    public Optional<String> getFirstAddQueue() {
        String peekFirst = this.receiveKeysQueue.peek();
        this.receiveKeysQueue.remove();
        return Optional.ofNullable(peekFirst);
    }

    public void addReceivedMessage(String key, String responseMsg) {
        if (this.sendMessageIdAndReceived.containsKey(key)) {
            CompletableFuture<String> future = this.sendMessageIdAndReceived.get(key);
            future.complete(responseMsg);
        }
    }

    public CompletableFuture<String> getResponseMsg(String key) {
        return this.sendMessageIdAndReceived.get(key);
    }

}
