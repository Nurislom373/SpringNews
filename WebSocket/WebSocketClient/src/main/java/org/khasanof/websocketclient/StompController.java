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
import java.util.List;
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
    private static final String URL_SIMPLE = "ws://localhost:8081/simple";
    private static final String SEND = "/app/chat";
    private static final String SEND_SIMPLE = "/simple";

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

        StompSession connectAsync = stompClient.connectAsync(URL, webSocketHttpHeaders, simpleStompSessionHandler)
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
        stompSession.subscribe("/topic/messages", new SimpleStompFrameHandler());
    }

    private void sendMessageAndReceive(StompSession stompSession) {
        stompSession.send(SEND, new MessageDTO("nurislom", "jeck pot"));
        stompSession.send(SEND, new MessageDTO("abdulloh", "new message"));
        stompSession.send(SEND_SIMPLE, new MessageDTO("hello", "Abdulloh"));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        connectSocket();
//        connectSimple();
    }
}
