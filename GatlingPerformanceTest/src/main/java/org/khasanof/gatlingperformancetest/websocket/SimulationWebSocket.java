package org.khasanof.gatlingperformancetest.websocket;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;

// required for Gatling HTTP DSL
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.websocket
 * @since 8/27/2023 4:30 PM
 */
public class SimulationWebSocket extends Simulation {

    private final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfRWU4dkZxdXpUVTJVRmM4VzlQX2lDaXFUZnZzUldUOEJvMXpudXRwMHhrIn0.eyJleHAiOjE2OTMyMTkxMzAsImlhdCI6MTY5MzIxODgzMCwianRpIjoiMWI0OWM2MmItMTYwNy00OWUzLThmNjQtYTk4ZTUxMzhiMDM3IiwiaXNzIjoiaHR0cHM6Ly9hdXRoLmIxbmsudXovcmVhbG1zL2hhbXlvbi1idXNpbmVzcyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIyNTg2ZGJhNS0xNWQzLTQ3NzAtYTE1NS04ZmY2YjM3MDg0NGYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJtaWNyb3NlcnZpY2UiLCJzZXNzaW9uX3N0YXRlIjoiNzk2ZjBlYWUtNDljMS00OWZmLWIxODUtYjc1YWNjYjA3ODk1IiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVFJBTlNBQ1RJT05TX0JZX1RFUk1JTkFMUyIsIlJPTEVfTEVHQUxfRU5USVRZIiwiUk9MRV9DT1JQX0NBUkRTIiwiUk9MRV9VUF9SRUxBVElPTl9DSEVDSyIsIlJPTEVfTU9OSVRPUklOR19ET0NVTUVOVCIsIlJPTEVfQ1VSUkVOVF9VU0VSX0ZFQVRVUkUiLCJST0xFX1VTRVJfUEFSVE5FUl9SRUwiLCJkZWZhdWx0LXJvbGVzLWhhbXlvbi1idXNpbmVzcyIsIlJPTEVfVFJBTlNBQ1RJT05TX0JZX1RPS0VOUyIsIlJPTEVfU01BTExfQlVTSU5FU1MiLCJST0xFX0dFVF9DTElFTlQiLCJST0xFX0FETUlOX0NPTVBBTlkiLCJST0xFX1VTRVIiLCJST0xFX1RSQU5TQUNUSU9OX0lOVk9JQ0UiLCJvZmZsaW5lX2FjY2VzcyIsIlJPTEVfQUNUSVZFX1VTRVJfUEFSVE5FUiIsIlJPTEVfR0VUX1RFUk1JTkFMUyIsIlJPTEVfVVBEQVRFX1RFUk1JTkFMUyIsInVtYV9hdXRob3JpemF0aW9uIiwiUk9MRV9VUF9SRUxBVElPTl9DUkVBVEUiLCJST0xFX1NJR05fT1JHQU5JWkFUSU9OUyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiI3OTZmMGVhZS00OWMxLTQ5ZmYtYjE4NS1iNzVhY2NiMDc4OTUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicm9sZXMiOlsiUk9MRV9UUkFOU0FDVElPTlNfQllfVEVSTUlOQUxTIiwiUk9MRV9MRUdBTF9FTlRJVFkiLCJST0xFX0NPUlBfQ0FSRFMiLCJST0xFX1VQX1JFTEFUSU9OX0NIRUNLIiwiUk9MRV9NT05JVE9SSU5HX0RPQ1VNRU5UIiwiUk9MRV9DVVJSRU5UX1VTRVJfRkVBVFVSRSIsIlJPTEVfVVNFUl9QQVJUTkVSX1JFTCIsImRlZmF1bHQtcm9sZXMtaGFteW9uLWJ1c2luZXNzIiwiUk9MRV9UUkFOU0FDVElPTlNfQllfVE9LRU5TIiwiUk9MRV9TTUFMTF9CVVNJTkVTUyIsIlJPTEVfR0VUX0NMSUVOVCIsIlJPTEVfQURNSU5fQ09NUEFOWSIsIlJPTEVfVVNFUiIsIlJPTEVfVFJBTlNBQ1RJT05fSU5WT0lDRSIsIm9mZmxpbmVfYWNjZXNzIiwiUk9MRV9BQ1RJVkVfVVNFUl9QQVJUTkVSIiwiUk9MRV9HRVRfVEVSTUlOQUxTIiwiUk9MRV9VUERBVEVfVEVSTUlOQUxTIiwidW1hX2F1dGhvcml6YXRpb24iLCJST0xFX1VQX1JFTEFUSU9OX0NSRUFURSIsIlJPTEVfU0lHTl9PUkdBTklaQVRJT05TIl0sInByZWZlcnJlZF91c2VybmFtZSI6IjM3MzM3MzM3MyJ9.FUj0TGRbl-qjrzV3wEBNZj-niPNsiUJ_v_bZxiGlTrZpGNR9KX0nctqUYAn1LlvibukN1q2ZC_wszeQUXNaVQgvFG9jaAEDjzB1Zm6eyH3k2wwDdQakbhwT1kUqa1n_xHx5JAce3zPF7U_NNtlOk8VpGNfAEnIfHai3bX2dhYWgQF9LDDARKdAmfkdgmFvsr6x3n_enQ7RURogOh_QhBPhhFQN5QOxWSSFMW2jK6bUufDVUWW4gZ3jIt8JYVR657e037Q8tNxD_AocQ1ZMYMOnD3rhpZDGVfCmPbue-f1AzbjCkltFSEgt1BGp3e1O34xaRNIjuDIzTsOJ8D5szd3g";
    private final String CONNECT_TEXT = "CONNECT\nAuthorization:Bearer ";
    private final String SEND_TEXT = "SEND\n" +
            "destination:/app/topic/handler\n" +
            "content-length:180\n" +
            "\n" +
            "{\"service\":\"PROFILEMS\",\"destination\":\"/app/topic/handler\",\"username\":\"373373373\",\"data\":{\"method\":\"getClientByUsername\",\"jsonrpc\":\"2.0\",\"id\":\"1\",\"params\":{\"username\":\"373373373\"}}}";


    public SimulationWebSocket() {
        setUp(wsScenario(TOKEN)
                .injectOpen(postEndpointInjectionProfile())
                .protocols(wsProtocol()));
    }

    private static HttpProtocolBuilder wsProtocol() {
        return http
                .baseUrl("https://api.b1nk.uz")
                .userAgentHeader("Gatling2")
                .wsBaseUrl("wss://api.b1nk.uz");
    }

    public ScenarioBuilder wsScenario(String token) {
        return scenario("plus-card")
                .pause(1)
                .exec(ws("connect")
                        .connect("/ws/websocket")
                        .header("Connection", "Upgrade")
                        .header("Origin", "https://plus.b1nk.uz")
                        .header("Upgrade", "WebSocket")
                        .header("Host", "api.b1nk.uz")
                        .header("Authorization", "Bearer ".concat(token))
                        .onConnected(
                                exec(ws("connect")
                                        .sendText(CONNECT_TEXT.concat(token))
                                        .await(10).on(
                                                ws.checkTextMessage("CONNECTED")
                                                        .check(regex("CONNECTED.*"))
                                        )
                                )
                                        .exec(ws("get brands")
                                                .sendText(SEND_TEXT)
                                                .await(10).on(
                                                        ws.checkTextMessage("MESSAGE")
                                                                .check(regex("MESSAGE.*"))
                                                )
                                        )
                        )
                );
    }


    private OpenInjectionStep.RampRate.RampRateOpenInjectionStep postEndpointInjectionProfile() {

        int totalDesiredUserCount = 200;
        double userRampUpPerInterval = 50;
        int totalRampUptimeSeconds = 10;

        return CoreDsl.rampUsersPerSec(userRampUpPerInterval)
                .to(totalDesiredUserCount)
                .during(Duration.ofSeconds(totalRampUptimeSeconds));
    }
}
