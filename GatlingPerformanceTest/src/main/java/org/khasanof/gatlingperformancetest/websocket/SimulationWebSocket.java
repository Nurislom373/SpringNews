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

    private final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJfRWU4dkZxdXpUVTJVRmM4VzlQX2lDaXFUZnZzUldUOEJvMXpudXRwMHhrIn0.eyJleHAiOjE2OTMxMzk4MzgsImlhdCI6MTY5MzEzOTUzOCwianRpIjoiMDI0YmQ2NWYtNTE3Yi00MDdiLThjNjEtYTYzYzYwY2U5MjBiIiwiaXNzIjoiaHR0cHM6Ly9hdXRoLmIxbmsudXovcmVhbG1zL2hhbXlvbi1idXNpbmVzcyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIyNTg2ZGJhNS0xNWQzLTQ3NzAtYTE1NS04ZmY2YjM3MDg0NGYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJtaWNyb3NlcnZpY2UiLCJzZXNzaW9uX3N0YXRlIjoiNDE2MWJlZTgtMjY3Zi00Njc1LWFiNWUtODcwOWVmOTYzZDc1IiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIlJPTEVfVFJBTlNBQ1RJT05TX0JZX1RFUk1JTkFMUyIsIlJPTEVfTEVHQUxfRU5USVRZIiwiUk9MRV9DT1JQX0NBUkRTIiwiUk9MRV9VUF9SRUxBVElPTl9DSEVDSyIsIlJPTEVfTU9OSVRPUklOR19ET0NVTUVOVCIsIlJPTEVfQ1VSUkVOVF9VU0VSX0ZFQVRVUkUiLCJST0xFX1VTRVJfUEFSVE5FUl9SRUwiLCJkZWZhdWx0LXJvbGVzLWhhbXlvbi1idXNpbmVzcyIsIlJPTEVfVFJBTlNBQ1RJT05TX0JZX1RPS0VOUyIsIlJPTEVfU01BTExfQlVTSU5FU1MiLCJST0xFX0dFVF9DTElFTlQiLCJST0xFX0FETUlOX0NPTVBBTlkiLCJST0xFX1VTRVIiLCJST0xFX1RSQU5TQUNUSU9OX0lOVk9JQ0UiLCJvZmZsaW5lX2FjY2VzcyIsIlJPTEVfQUNUSVZFX1VTRVJfUEFSVE5FUiIsIlJPTEVfR0VUX1RFUk1JTkFMUyIsIlJPTEVfVVBEQVRFX1RFUk1JTkFMUyIsInVtYV9hdXRob3JpemF0aW9uIiwiUk9MRV9VUF9SRUxBVElPTl9DUkVBVEUiLCJST0xFX1NJR05fT1JHQU5JWkFUSU9OUyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiI0MTYxYmVlOC0yNjdmLTQ2NzUtYWI1ZS04NzA5ZWY5NjNkNzUiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicm9sZXMiOlsiUk9MRV9UUkFOU0FDVElPTlNfQllfVEVSTUlOQUxTIiwiUk9MRV9MRUdBTF9FTlRJVFkiLCJST0xFX0NPUlBfQ0FSRFMiLCJST0xFX1VQX1JFTEFUSU9OX0NIRUNLIiwiUk9MRV9NT05JVE9SSU5HX0RPQ1VNRU5UIiwiUk9MRV9DVVJSRU5UX1VTRVJfRkVBVFVSRSIsIlJPTEVfVVNFUl9QQVJUTkVSX1JFTCIsImRlZmF1bHQtcm9sZXMtaGFteW9uLWJ1c2luZXNzIiwiUk9MRV9UUkFOU0FDVElPTlNfQllfVE9LRU5TIiwiUk9MRV9TTUFMTF9CVVNJTkVTUyIsIlJPTEVfR0VUX0NMSUVOVCIsIlJPTEVfQURNSU5fQ09NUEFOWSIsIlJPTEVfVVNFUiIsIlJPTEVfVFJBTlNBQ1RJT05fSU5WT0lDRSIsIm9mZmxpbmVfYWNjZXNzIiwiUk9MRV9BQ1RJVkVfVVNFUl9QQVJUTkVSIiwiUk9MRV9HRVRfVEVSTUlOQUxTIiwiUk9MRV9VUERBVEVfVEVSTUlOQUxTIiwidW1hX2F1dGhvcml6YXRpb24iLCJST0xFX1VQX1JFTEFUSU9OX0NSRUFURSIsIlJPTEVfU0lHTl9PUkdBTklaQVRJT05TIl0sInByZWZlcnJlZF91c2VybmFtZSI6IjM3MzM3MzM3MyJ9.RUO49Rlhlf93O4SkT-6xniY5-xdSlovll3UFAnfhDfQ5ZNUjSTdOjRbOikc12rROod3HhS1rBGSD-5B-RRq5DD-mzSljtBT9SK4bbsqpW0s1C63sYVx6doG7Mkb-hswklkFRK0Bu4M7VFgsQpu1Z13NjbH-fY6plqg2pRCFZkYtHE5obANFk2V5hSYXjfwhywyOgZf3HM1EXcKb67NPodLX-muZNqFzymOkMOArECncGfO5L5fhEOdxt031phpauvX-7FRHUDWMGDp7U-lQhGQltxOHbHhIvM5yOWXfVInOrDYefS7fF5NQqsgWEUHtZWUiGRt6BO8R8t7bB5bFJfA";
    private final String CONNECT_TEXT = "CONNECT\nAuthorization:Bearer\n";
    private final String SEND_TEXT = "SEND\\ndestination:/app/topic/handler\\ncontent-length:238\\n\\n{\\\"service\\\":\\\"FUELMS\\\",\\\"destination\\\":\\\"/app/topic/handler\\\",\\\"username\\\":\\\"373373373\\\",\\\"data\\\":{\\\"method\\\":\\\"getBrand\\\",\\\"jsonrpc\\\":\\\"2.0\\\",\\\"id\\\":\\\"1\\\",\\\"params\\\":{\\\"pageable\\\":{\\\"page\\\":0,\\\"size\\\":10,\\\"sort\\\":null,\\\"orderBy\\\":\\\"DESC\\\",\\\"properties\\\":[\\\"id\\\"]},\\\"criteria\\\":{}}}}\\u0000";

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
                        .connect("/app/topic/handler")
                        .header("Connection", "Upgrade")
                        .header("Origin", "https://plus.b1nk.uz")
                        .header("Upgrade", "WebSocket")
                        .header("Host", "api.b1nk.uz")
                        .onConnected(
                                exec(ws("connect")
                                        .sendText(CONNECT_TEXT.concat(token))
                                        .await(5).on(
                                                ws.checkTextMessage("CONNECTED")
                                                        .check(regex("CONNECTED.*"))
                                        )
                                )
                                        .exec(ws("get brands")
                                        .sendText(SEND_TEXT)
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
