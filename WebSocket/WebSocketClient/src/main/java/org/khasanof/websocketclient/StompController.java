package org.khasanof.websocketclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Nurislom
 * @see org.khasanof.websocket
 * @since 8/30/2023 6:15 PM
 */
@Slf4j
@Service
public class StompController implements ApplicationRunner {

    private static final String URL = "ws://localhost:8081/ws";
    private static final String URL_SIMPLE = "ws://localhost:8088/websocket/tracker?access_token=";
    private static final String SEND = "/app/handler";
    private static final String SEND_SIMPLE = "/simple";
    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY5ODkwNTQ5Nn0.1RgQ_swjjg8kpwEH_WdnFNuUCtjnnWnGCCqZfDb1xa6GW9OzyOETIGad9SgTtPWBm1pu9g3rexXpk2346uOCJA";

    private final WsResponseSessionHandler handler = new WsResponseSessionHandler();

    public void connectSocket() throws ExecutionException, InterruptedException, TimeoutException {
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        WebSocketClient webSocketClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

        SimpleStompSessionHandler simpleStompSessionHandler = new SimpleStompSessionHandler();

        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
//        webSocketHttpHeaders.add("login", "nurislom");
//        webSocketHttpHeaders.add("password", "123");

        StompSession connectAsync = stompClient.connectAsync(URL_SIMPLE.concat(token), simpleStompSessionHandler)
                .get(5, TimeUnit.SECONDS);

        subscribeAfterConnected(connectAsync);
        sendOpenConnection(connectAsync);
//        startTransactionSimulateFail(connectAsync);
//        startTransactionSimulateFail(connectAsync);
        Thread.sleep(6000);
//        stopTransactionSimulateFail(connectAsync);

//        sendMessageAndReceive(connectAsync);
        log.info("disconnect after 3 second!");
//        connectAsync.disconnect();
    }

    public void connectSimple() throws ExecutionException, InterruptedException {
        log.info("Connection start simple!");
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

        SimpleStompSessionHandler simpleStompSessionHandler = new SimpleStompSessionHandler();

        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.add("Host", "Test");
        webSocketHttpHeaders.add("Upgrade", "websocket");
        webSocketHttpHeaders.add("Connection", "Upgrade");

        StompSession connectAsync = stompClient.connectAsync(URL_SIMPLE, simpleStompSessionHandler)
                .get();
        System.out.println(connectAsync);
        log.info("disconnect after 3 second!");
        sendSimpleUrl(connectAsync);
        Thread.sleep(3000);
    }

    public void sendSimpleUrl(StompSession stompSession) {
        StompHeaders headers = new StompHeaders();
        headers.setDestination("test");
        headers.setMessageId("123");
        stompSession.send(headers, new MessageDTO("Abror", "Hello World"));
    }

    private void subscribeAfterConnected(StompSession stompSession) {
        log.info("Start Subscribe topics");
        stompSession.subscribe("/topic/messages", new WsResponseSessionHandler());
        stompSession.subscribe("/user/topic/messages", handler);
        log.info("End Subscribe topics");
    }

    private void sendMessageAndReceive(StompSession stompSession) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sendNearChargeBoxes(stompSession);
        log.info("Successfully send message!");
    }

    private void stopTransactionSimulate(StompSession stompSession) {
        Map<String, Object> map1 = Map.of(
                "chargeBoxId", "1",
                "transactionId", handler.getId(),
                "idTag", "123",
                "reason", "EV_DISCONNECTED",
                "stopMeterValue", "4000"
        );
        Map<String, Object> objectMap2 = Map.of(
                "method", "STOP_TRANSACTION_SIMULATE",
                "id", "7845362",
                "payload", map1
        );
        stompSession.send(SEND, objectMap2);
    }

    private void stopTransactionSimulateFail(StompSession stompSession) {
        Map<String, Object> map1 = Map.of(
                "chargeBoxId", "1543543",
                "transactionId", 5454565,
                "idTag", "12334243",
                "reason", "EV_DISCONNECTED",
                "stopMeterValue", "4000"
        );
        Map<String, Object> objectMap2 = Map.of(
                "method", "STOP_TRANSACTION_SIMULATE",
                "id", "7845362",
                "payload", map1
        );
        stompSession.send(SEND, objectMap2);
    }

    private void sendOpenConnection(StompSession stompSession) {
        Map<String, Object> map = new HashMap<>();
        map.put("method", "OPEN_CONNECTION");
        map.put("id", "123");
        map.put("payload", new HashMap<>() {{
            put("chargeBoxId", "1");
        }});
        stompSession.send(SEND, map);
    }

    private void startTransactionSimulate(StompSession stompSession) {
        Map<String, Object> dataMap = Map.of(
                "chargeBoxId", 1,
                "connectorId", 49944,
                "idTag", 123
        );
        Map<String, Object> objectMap = Map.of(
                "method", "START_TRANSACTION_SIMULATE",
                "id", "123",
                "payload", dataMap
        );
        stompSession.send(SEND, objectMap);
    }

    private void startTransactionSimulateFail(StompSession stompSession) {
        Map<String, Object> dataMap = Map.of(
                "chargeBoxId", 1,
                "connectorId", 49944,
                "idTag", 1234
        );
        Map<String, Object> objectMap1 = new HashMap<>() {{
            put("chargeBoxId", null);
            put("connectorId", null);
            put("idTag", null);
        }};
        Map<String, Object> objectMap = Map.of(
                "method", "START_TRANSACTION_SIMULATE",
                "id", "123",
                "payload", objectMap1
        );
        stompSession.send(SEND, objectMap);
    }

    private void sendNearChargeBoxes(StompSession stompSession) {
        Map<String, Object> objectMap2 = Map.of(
                "method", "SEARCH_NEARBY_CHARGE_BOXES",
                "id", "64587346",
                "payload", Map.of(
                        "latitude", 83656.00000000,
                        "longitude", 17379.00000000,
                        "distance", 50.0000
                )
        );
        stompSession.send(SEND, objectMap2);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        connectSocket();
//        connectSimple();
    }
}
