package org.khasanof.gatlingperformancetest.check_pay.wss;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.client.ahc
 * @since 9/2/2023 9:07 PM
 */
public interface WSS {

    String sendMessage(String message);

    CompletableFuture<String> getMsgResponse(String msgKey);

    CompletableFuture<Map<String, Object>> getMsgResponseToMap(String msgKey);

    <T> CompletableFuture<T> getMsgResponseToType(String msgKey, Class<T> clazz);

}
