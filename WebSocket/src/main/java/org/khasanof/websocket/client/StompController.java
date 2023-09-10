package org.khasanof.websocket.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.khasanof.websocket.client.dto.JsonRpcRequest;
import org.khasanof.websocket.client.dto.WsData;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Nurislom
 * @see org.khasanof.websocket
 * @since 8/30/2023 6:15 PM
 */
@Slf4j
//@Service
public class StompController implements ApplicationRunner {

    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfRWU4dkZxdXpUVTJVRmM4VzlQX2lDaXFUZnZzUldUOEJvMXpudXRwMHhrIn0.eyJleHAiOjE2OTM0Nzg0MTMsImlhdCI6MTY5MzQ3ODExMywianRpIjoiMjVjYWI5ZjgtODlmYi00YzYyLWFmMTctZmUzZTViODM3YzYzIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLmIxbmsudXovcmVhbG1zL2hhbXlvbi1idXNpbmVzcyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIyNTg2ZGJhNS0xNWQzLTQ3NzAtYTE1NS04ZmY2YjM3MDg0NGYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJtaWNyb3NlcnZpY2UiLCJzZXNzaW9uX3N0YXRlIjoiMDk4YzZmYmMtZThjZi00ZTMyLWFlNjItZjY3MmYzY2UzNDA3IiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVFJBTlNBQ1RJT05TX0JZX1RFUk1JTkFMUyIsIlJPTEVfTEVHQUxfRU5USVRZIiwiUk9MRV9DT1JQX0NBUkRTIiwiUk9MRV9VUF9SRUxBVElPTl9DSEVDSyIsIlJPTEVfTU9OSVRPUklOR19ET0NVTUVOVCIsIlJPTEVfQ1VSUkVOVF9VU0VSX0ZFQVRVUkUiLCJST0xFX1VTRVJfUEFSVE5FUl9SRUwiLCJkZWZhdWx0LXJvbGVzLWhhbXlvbi1idXNpbmVzcyIsIlJPTEVfVFJBTlNBQ1RJT05TX0JZX1RPS0VOUyIsIlJPTEVfU01BTExfQlVTSU5FU1MiLCJST0xFX0dFVF9DTElFTlQiLCJST0xFX0FETUlOX0NPTVBBTlkiLCJST0xFX1VTRVIiLCJST0xFX1RSQU5TQUNUSU9OX0lOVk9JQ0UiLCJvZmZsaW5lX2FjY2VzcyIsIlJPTEVfQUNUSVZFX1VTRVJfUEFSVE5FUiIsIlJPTEVfR0VUX1RFUk1JTkFMUyIsIlJPTEVfVVBEQVRFX1RFUk1JTkFMUyIsInVtYV9hdXRob3JpemF0aW9uIiwiUk9MRV9VUF9SRUxBVElPTl9DUkVBVEUiLCJST0xFX1NJR05fT1JHQU5JWkFUSU9OUyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiIwOThjNmZiYy1lOGNmLTRlMzItYWU2Mi1mNjcyZjNjZTM0MDciLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicm9sZXMiOlsiUk9MRV9UUkFOU0FDVElPTlNfQllfVEVSTUlOQUxTIiwiUk9MRV9MRUdBTF9FTlRJVFkiLCJST0xFX0NPUlBfQ0FSRFMiLCJST0xFX1VQX1JFTEFUSU9OX0NIRUNLIiwiUk9MRV9NT05JVE9SSU5HX0RPQ1VNRU5UIiwiUk9MRV9DVVJSRU5UX1VTRVJfRkVBVFVSRSIsIlJPTEVfVVNFUl9QQVJUTkVSX1JFTCIsImRlZmF1bHQtcm9sZXMtaGFteW9uLWJ1c2luZXNzIiwiUk9MRV9UUkFOU0FDVElPTlNfQllfVE9LRU5TIiwiUk9MRV9TTUFMTF9CVVNJTkVTUyIsIlJPTEVfR0VUX0NMSUVOVCIsIlJPTEVfQURNSU5fQ09NUEFOWSIsIlJPTEVfVVNFUiIsIlJPTEVfVFJBTlNBQ1RJT05fSU5WT0lDRSIsIm9mZmxpbmVfYWNjZXNzIiwiUk9MRV9BQ1RJVkVfVVNFUl9QQVJUTkVSIiwiUk9MRV9HRVRfVEVSTUlOQUxTIiwiUk9MRV9VUERBVEVfVEVSTUlOQUxTIiwidW1hX2F1dGhvcml6YXRpb24iLCJST0xFX1VQX1JFTEFUSU9OX0NSRUFURSIsIlJPTEVfU0lHTl9PUkdBTklaQVRJT05TIl0sInByZWZlcnJlZF91c2VybmFtZSI6IjM3MzM3MzM3MyJ9.BptR91vB2jI2CCspGEJ_WY-dKJxQVTDTd1Rs-7J7nfOrT3TplEP4C7W2NOf9ZcjijPajjDm_m2tumlQim-NiVicyn6mOahVTXiyDJNBFmtBDfasdNFI8yqNf96T8xoS4jMz8ZW2zUsiGxg844UHCO-jIIgujeZA4oencZSPV6d1X8LmGmTcJ501J-nOeWgdR22__eSTlevapnszftppbi8HuE4SGZVbZSzpUNZthIsSvJt3MWBhmH23_yc2cASr_cjf9_qHxpgpn-mNW8a1JG8ew3yIG3z-l6qV8KfJZgAItnHUMMQuI-v6X_Qf9qATOLp2_BLNLQyw6j_dpkNpQ_A";
    private static final String URL = "wss://api.b1nk.uz/ws";
    private static final String SEND = "/app/topic/handler";

    public void connectSocket(String token) throws ExecutionException, InterruptedException, TimeoutException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());

        SimpleStompSessionHandler simpleStompSessionHandler = new SimpleStompSessionHandler();

        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
        webSocketHttpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token));
        webSocketHttpHeaders.set("Origin", "https://plus.b1nk.uz");
        webSocketHttpHeaders.set("Upgrade", "websocket");
        webSocketHttpHeaders.set("Host", "api.b1nk.uz");
        webSocketHttpHeaders.set("User-Agent", "spring");
        webSocketHttpHeaders.set("Connection", "upgrade");
        StompHeaders headers = new StompHeaders();
        headers.add("Authorization", "Bearer ".concat(token)
                .concat("\\naccept-version:1.1,1.0\\nheart-beat:10000,10000\\n\\n\\u0000"));

        StompSession connectAsync = stompClient.connectAsync(getURL(), webSocketHttpHeaders, headers, simpleStompSessionHandler)
                .get();

        subscribeAfterConnected(connectAsync);
//        sendMessageAndReceive(connectAsync);
        log.info("disconnect after 3 second!");
        Thread.sleep(3000);
        connectAsync.disconnect();
    }

    private String getURL() {
        return "wss://api.b1nk.uz/ws/" + RandomUtils.nextInt(100, 1000) + "/" +
                RandomStringUtils.randomAlphanumeric(8) + "/websocket";
    }

    private void subscribeAfterConnected(StompSession stompSession) {
        stompSession.subscribe("/app/topic/send", new SimpleStompFrameHandler());
        stompSession.subscribe("/user/topic/rpc", new SimpleStompFrameHandler());
    }

    private void sendMessageAndReceive(StompSession stompSession) {
        stompSession.send(SEND, jsonRpcRequest());
    }

    private WsData<JsonRpcRequest> jsonRpcRequest() {
        WsData<JsonRpcRequest> wsData = new WsData<>();
        wsData.setDestination(SEND);
        wsData.setUsername("373373373");
        wsData.setService("PAYMENTMS");
        wsData.setData(new JsonRpcRequest("1", "2.0", "getPayment", defaultCriteria()));
        return wsData;
    }

    private Map<String, Map<String, Object>> defaultCriteria() {
        return new HashMap<>(){{
           put("pageable", new HashMap<>(){{
               put("page", 0);
               put("size", 10);
               put("sort", null);
               put("orderBy", "DESC");
               put("properties", List.of("id"));
           }});
           put("criteria", new HashMap<>());
        }};
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        connectSocket(TOKEN);
    }
}
