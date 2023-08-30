package org.khasanof.gatlingperformancetest.websocket;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.ws;
import static org.khasanof.gatlingperformancetest.websocket.HttpClientCreate.getToken;
import static org.khasanof.gatlingperformancetest.websocket.HttpClientCreate.register;
import static org.khasanof.gatlingperformancetest.websocket.JsonRpcConstants.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.websocket
 * @since 8/29/2023 11:02 AM
 */
public class PlusWebSocket extends Simulation {

    private final long TIMEOUT = 20;
    private static final String DEPARTMENT = "department";
    private final ThreadLocal<String> departmentLocal = ThreadLocal.withInitial(() -> "");

    {
        List<PopulationBuilder> builders = IntStream.range(0, 2)
                .parallel()
                .mapToObj(i -> {
                    Optional<RegisterDTO> optional = register();
                    if (optional.isPresent()) {
                        RegisterDTO registerDTO = optional.get();
                        System.out.println("registerDTO = " + registerDTO);
                        Optional<String> optional1 = getToken(registerDTO);
                        if (optional1.isPresent()) {
                            String token = optional1.get();
                            return scenarioBuilder(registerDTO.getInn(), token,
                                    registerDTO.getPartnerType().equals(RegisterDTO.PartnerType.BUSINESS))
                                    .injectOpen(atOnceUsers(1))
                                    .protocols(wsProtocol(token));
                        }
                    }
                    return null;
                }).filter(Objects::nonNull).toList();
        System.out.println("##### Populations ready test! #####");
        setUp(builders);
    }

    private ScenarioBuilder scenarioBuilder(String username, String token, boolean isBusiness) {
        return scenario(RandomStringUtils.randomAlphanumeric(9))
                .exec(ws("Open WebSocket")
                        .connect("/ws/".concat(String.valueOf(RandomUtils.nextInt(100, 1000)))
                                .concat("/").concat(RandomStringUtils.randomAlphanumeric(8))
                                .concat("/").concat("websocket"))
                ).pause(1)
                .exec(ws("Connect")
                        .sendText("[\"CONNECT\\nAuthorization:Bearer " + token +
                                "\\naccept-version:1.1,1.0\\nheart-beat:10000,10000\\n\\n\\u0000\"]")
                        .await(5).on(
                                ws.checkTextMessage("check connected")
                                        .check(regex("CONNECTED.*"))
                        )
                )
                .pause(1)
                .exec(
                        exec(ws("Sub-0")
                                .sendText("[\"SUBSCRIBE\\nid:sub-0\\ndestination:/app/topic/send\\n\\n\\u0000\"]")),
                        exec(ws("Sub-1")
                                .sendText("[\"SUBSCRIBE\\nid:sub-1\\ndestination:/user/topic/rpc\\n\\n\\u0000\"]"))
                )
                .pause(2)
                .exec(doIfOrElse(in -> isBusiness)
                        .then(businessChainBuilder(username))
                        .orElse(partnerChainBuilder(username))
                ).exec(ws("close ws").close());

    }

    private ChainBuilder businessChainBuilder(String username) {
        return exec(
                exec(repeat(3)
                        .on(exec(
                                ws("Send Message")
                                        .sendText(formatter(PAYMENT_MS_GET_TRANS, Map.of("username", username)))
                                        .await(TIMEOUT).on(
                                                ws.checkTextMessage("check message")
                                                        .check(regex("MESSAGE.*"))
                                        )
                        ))
                ),
                exec(pause(1)),
                exec(ws("Send Message")
                        .sendText(formatter(PROFILE_MS_GET_DEPARTMENTS, Map.of("username", username)))
                        .await(TIMEOUT).on(
                                ws.checkTextMessage("check message")
                                        .check(regex("MESSAGE.*"))
                        )
                ),
                exec(pause(1)),
                exec(ws("Send Message")
                                .sendText(formatter(PROFILE_MS_CREATE_DEPARTMENT, Map.of(
                                        "username", username,
                                        "code", RandomStringUtils.randomAlphanumeric(6),
                                        "nameUz", RandomStringUtils.randomAlphanumeric(9),
                                        "nameRu", RandomStringUtils.randomAlphanumeric(9)
                                )))
                                .await(TIMEOUT).on(
                                        ws.checkTextMessage("check message")
                                                .check(regex("MESSAGE.*"))
                                )
                )
        );
    }

    private ChainBuilder partnerChainBuilder(String username) {
        return exec(
                exec(repeat(3)
                        .on(exec(
                                ws("Send Message")
                                        .sendText(PRODUCT_MS_MERCHANT_OFFERS)
                                        .await(TIMEOUT).on(
                                                ws.checkTextMessage("Check Message")
                                                        .check(regex("MESSAGE.*"))
                                        )
                        ))),
                exec(pause(1)),
                exec(ws("Send Message")
                        .sendText(PRODUCT_MS_GET_CATEGORIES)
                        .await(TIMEOUT).on(
                                ws.checkTextMessage("check message")
                                        .check(regex("MESSAGE.*"))
                        )),
                exec(pause(1)),
                exec(ws("Send Message")
                        .sendText(formatter(PRODUCT_MS_CREATE_CATEGORY, Map.of(
                                "username", username,
                                "nameUz", RandomStringUtils.randomAlphanumeric(9),
                                "nameRu", RandomStringUtils.randomAlphanumeric(9)
                        )))
                        .await(TIMEOUT).on(
                                ws.checkTextMessage("check message")
                                        .check(regex("MESSAGE.*").saveAs("key"))
                        )
                )
        );
    }


    private HttpProtocolBuilder wsProtocol(String token) {
        return http
                .baseUrl("https://api.b1nk.uz")
                .header("Authorization", "Bearer ".concat(token))
                .userAgentHeader("Gatling")
                .wsBaseUrl("wss://api.b1nk.uz");
    }

}
