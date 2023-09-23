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
    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY5NTM3NDM1M30.pSOrfjPiiCsmhU0axxoYBUEs4UmnSGsPMQSmD3oqR17KxShBJs129pWXbdRgjW9WzbvzKZpGUQjprf2O0HegiA";

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
        sendMessageAndReceive(connectAsync);
        log.info("disconnect after 3 second!");
        Thread.sleep(3000);
        connectAsync.disconnect();
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
        stompSession.subscribe("/topic/messages", new SimpleStompFrameHandler());
        stompSession.subscribe("/user/topic/updates", new SimpleStompFrameHandler());
        log.info("End Subscribe topics");
    }

    private void sendMessageAndReceive(StompSession stompSession) {
        Map<String, Object> map = new HashMap<>();
        map.put("method", "OPEN_CONNECTION");
        map.put("id", "123");
        map.put("payload", new HashMap<>(){{
            put("chargeBoxId", "10");
        }});
        stompSession.send(SEND, map);
        log.info("Successfully send message!");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        connectSocket();
//        connectSimple();
    }
}
