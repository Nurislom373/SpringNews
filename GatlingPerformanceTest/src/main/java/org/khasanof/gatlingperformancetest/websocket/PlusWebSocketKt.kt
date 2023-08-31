package org.khasanof.gatlingperformancetest.websocket

import io.gatling.javaapi.core.*
import io.gatling.javaapi.http.HttpDsl
import io.gatling.javaapi.http.HttpProtocolBuilder
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import java.util.*
import java.util.Map
import java.util.stream.IntStream

class PlusWebSocketKt : Simulation() {

    companion object {
        const val TIMEOUT: Long = 20
    }

    init {
        val builders = IntStream.range(0, 2)
            .parallel()
            .mapToObj<PopulationBuilder> { _: Int ->
                val optional = HttpClientCreate.register()
                if (optional.isPresent) {
                    val registerDTO = optional.get()
                    println("registerDTO = $registerDTO")
                    val optional1 = HttpClientCreate.getToken(registerDTO)
                    if (optional1.isPresent) {
                        val token = optional1.get()
                        return@mapToObj scenarioBuilder(
                            registerDTO.inn,
                            token,
                            registerDTO.partnerType == RegisterDTO.PartnerType.BUSINESS
                        ).injectOpen(CoreDsl.atOnceUsers(1))
                            .protocols(wsProtocol(token))
                    }
                }
                null
            }.filter { obj: PopulationBuilder? ->
                Objects.nonNull(
                    obj
                )
            }.toList()
        println("##### Populations ready test! #####")
        setUp(builders)
    }

    private fun scenarioBuilder(username: String, token: String, isBusiness: Boolean): ScenarioBuilder {
        return CoreDsl.scenario(RandomStringUtils.randomAlphanumeric(9))
            .exec(
                HttpDsl.ws("Open WebSocket")
                    .connect(
                        "/ws/" + RandomUtils.nextInt(100, 1000).toString() + "/" + RandomStringUtils.randomAlphanumeric(
                            8
                        ) + "/" + "websocket"
                    )
            ).pause(1)
            .exec(
                HttpDsl.ws("Connect")
                    .sendText(
                        "[\"CONNECT\\nAuthorization:Bearer " + token +
                                "\\naccept-version:1.1,1.0\\nheart-beat:10000,10000\\n\\n\\u0000\"]"
                    )
                    .await(5).on(
                        HttpDsl.ws.checkTextMessage("check connected")
                            .check(CoreDsl.regex("CONNECTED.*"))
                    )
            )
            .pause(1)
            .exec(
                CoreDsl.exec(
                    HttpDsl.ws("Sub-0")
                        .sendText("[\"SUBSCRIBE\\nid:sub-0\\ndestination:/app/topic/send\\n\\n\\u0000\"]")
                ),
                CoreDsl.exec(
                    HttpDsl.ws("Sub-1")
                        .sendText("[\"SUBSCRIBE\\nid:sub-1\\ndestination:/user/topic/rpc\\n\\n\\u0000\"]")
                )
            )
            .pause(2)
            .exec(
                CoreDsl.doIfOrElse { isBusiness }
                    .then((businessChainBuilder(username)))
                    .orElse((partnerChainBuilder(username)))
            ).exec(HttpDsl.ws("close ws").close())
    }

    private fun businessChainBuilder(username: String): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.exec(
                CoreDsl.repeat(3)
                    .on(
                        CoreDsl.exec(
                            HttpDsl.ws("Send Message Get Payment")
                                .sendText(
                                    JsonRpcConstants.formatter(
                                        JsonRpcConstants.PAYMENT_MS_GET_TRANS,
                                        Map.of<String, String>("username", username)
                                    )
                                )
                                .await(TIMEOUT).on(
                                    HttpDsl.ws.checkTextMessage("check message")
                                        .check(CoreDsl.regex("MESSAGE.*"))
                                )
                        )
                    )
            ),
            CoreDsl.exec(CoreDsl.pause(1)),
            CoreDsl.exec(
                HttpDsl.ws("Send Message Get Departments")
                    .sendText(
                        JsonRpcConstants.formatter(
                            JsonRpcConstants.PROFILE_MS_GET_DEPARTMENTS,
                            Map.of<String, String>("username", username)
                        )
                    )
                    .await(TIMEOUT).on(
                        HttpDsl.ws.checkTextMessage("check message")
                            .check(CoreDsl.regex("MESSAGE.*"))
                    )
            ),
            CoreDsl.exec(CoreDsl.pause(1)),
            CoreDsl.exec(
                HttpDsl.ws("Send Message Create De Departments")
                    .sendText(
                        JsonRpcConstants.formatter(
                            JsonRpcConstants.PROFILE_MS_CREATE_DEPARTMENT, Map.of<String, String>(
                                "username", username,
                                "code", RandomStringUtils.randomAlphanumeric(6),
                                "nameUz", RandomStringUtils.randomAlphanumeric(9),
                                "nameRu", RandomStringUtils.randomAlphanumeric(9)
                            )
                        )
                    )
                    .await(TIMEOUT).on(
                        HttpDsl.ws.checkTextMessage("check message")
                            .check(CoreDsl.regex("MESSAGE.*"))
                    )
            )
        )
    }

    private fun partnerChainBuilder(username: String): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.exec(
                CoreDsl.repeat(3)
                    .on(
                        CoreDsl.exec(
                            HttpDsl.ws("Send Message Get Merchant Offers")
                                .sendText(JsonRpcConstants.PRODUCT_MS_MERCHANT_OFFERS)
                                .await(TIMEOUT).on(
                                    HttpDsl.ws.checkTextMessage("Check Message")
                                        .check(CoreDsl.regex("MESSAGE.*"))
                                )
                        )
                    )
            ),
            CoreDsl.exec(CoreDsl.pause(1)),
            CoreDsl.exec(
                HttpDsl.ws("Send Message Get Categories")
                    .sendText(JsonRpcConstants.PRODUCT_MS_GET_CATEGORIES)
                    .await(TIMEOUT).on(
                        HttpDsl.ws.checkTextMessage("check message")
                            .check(CoreDsl.regex("MESSAGE.*"))
                    )
            ),
            CoreDsl.exec(CoreDsl.pause(1)),
            CoreDsl.exec(
                HttpDsl.ws("Send Message Create Category")
                    .sendText(
                        JsonRpcConstants.formatter(
                            JsonRpcConstants.PRODUCT_MS_CREATE_CATEGORY, Map.of<String, String>(
                                "username", username,
                                "nameUz", RandomStringUtils.randomAlphanumeric(9),
                                "nameRu", RandomStringUtils.randomAlphanumeric(9)
                            )
                        )
                    )
                    .await(TIMEOUT).on(
                        HttpDsl.ws.checkTextMessage("check message")
                            .check(CoreDsl.regex("MESSAGE.*").saveAs("key"))
                    )
            )
        )
    }

    private fun wsProtocol(token: String): HttpProtocolBuilder {
        return HttpDsl.http
            .baseUrl("https://api.b1nk.uz")
            .header("Authorization", "Bearer $token")
            .userAgentHeader("Gatling")
            .wsBaseUrl("wss://api.b1nk.uz")
    }

}
