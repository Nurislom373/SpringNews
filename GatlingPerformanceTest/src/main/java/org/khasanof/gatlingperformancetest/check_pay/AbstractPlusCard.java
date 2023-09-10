package org.khasanof.gatlingperformancetest.check_pay;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.check_pay
 * @since 9/4/2023 11:35 AM
 */
public abstract class AbstractPlusCard extends Simulation {

    protected HttpProtocolBuilder wsProtocol(String token) {
        return http
                .baseUrl("https://api.b1nk.uz")
                .header("Authorization", "Bearer ".concat(token))
                .userAgentHeader("Gatling")
                .wsBaseUrl("wss://api.b1nk.uz");
    }

}
