package org.khasanof.websocket.ahc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.khasanof.websocket.dto.JsonRpcResponse;
import org.springframework.http.HttpHeaders;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.ahc
 * @since 8/31/2023 2:37 PM
 */
@Slf4j
public class AsyncHttpClientWebSocket {

    private final PlusWebSocketCompletable nettyWebSocketUniquer = new PlusWebSocketCompletable();
    private final PlusWebSocketListener listener = new PlusWebSocketListener(nettyWebSocketUniquer);
    private final MessageConverter messageConverter = new MessageConverter();
    private final String SUB_0 = "[\"SUBSCRIBE\\nid:sub-0\\ndestination:/app/topic/send\\n\\n\\u0000\"]";
    private final String SUB_1 = "[\"SUBSCRIBE\\nid:sub-1\\ndestination:/user/topic/rpc\\n\\n\\u0000\"]";
    private final String SEND_TRANSACTION = "[\"SEND\\ndestination:/app/topic/handler\\ncontent-length:261\\n\\n{\\\"service\\\":\\\"PRODUCTMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"373373373\\\",\\\"data\\\":{\\\"method\\\":\\\"getAllMerchantsProductOffers\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"pageable\\\":{\\\"page\\\":0,\\\"size\\\":10,\\\"sort\\\":null,\\\"orderBy\\\":\\\"DESC\\\",\\\"properties\\\":[\\\"id\\\"]},\\\"criteria\\\":{}}}}\\u0000\"]";

    public void builder(String token) throws ExecutionException, InterruptedException {
        NettyWebSocket webSocket = Dsl.asyncHttpClient()
                .prepareGet(getURL())
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token))
                .setRequestTimeout(5000)
                .execute(PlusListener.buildUpgradeHandler(listener))
                .get();

        if (webSocket.isOpen() && webSocket.isReady()) {
            webSocket.sendTextFrame("[\"CONNECT\\nAuthorization:Bearer " + token +
                    "\\naccept-version:1.1,1.0\\nheart-beat:10000,10000\\n\\n\\u0000\"]");
            nettyWebSocketUniquer.sendMessage(webSocket, SUB_0);
            nettyWebSocketUniquer.sendMessage(webSocket, SUB_1);
            String lastKey = nettyWebSocketUniquer.sendMessage(webSocket, SEND_TRANSACTION);
            Thread.sleep(1000);
            CompletableFuture<String> future = nettyWebSocketUniquer.getResponseMsg(lastKey);
            if (future.isDone()) {
                log.info("Message found!!!");
                String message = future.get();
                System.out.println("message = " + message);
                JsonRpcResponse jsonRpcResponse = messageConverter.convertToType(message, JsonRpcResponse.class);
                System.out.println("jsonRpcResponse = " + jsonRpcResponse);
            } else {
                log.warn("Message not found!!!");
            }
        }

        Thread.sleep(3000);
        webSocket.sendCloseFrame();
    }

    private String getURL() {
        return "wss://api.b1nk.uz/ws/" + RandomUtils.nextInt(100, 1000) + "/" +
                RandomStringUtils.randomAlphanumeric(8) + "/websocket";
    }

    private WebSocketUpgradeHandler getUpgradeHandler(CompletableFuture<String> future) {
        WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();
        WebSocketUpgradeHandler upgradeHandler = builder.addWebSocketListener(new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                log.info("websocket connection opened!");
            }

            @Override
            public void onTextFrame(String payload, boolean finalFragment, int rsv) {
                log.info("reply received : {}", payload);
                future.complete(payload);
            }

            @Override
            public void onClose(WebSocket webSocket, int i, String s) {
                log.info("websocket connection closed!");
            }

            @Override
            public void onError(Throwable throwable) {
                log.warn("exception throwing! : {}", throwable.getMessage());
            }
        }).build();
        return upgradeHandler;
    }

}
