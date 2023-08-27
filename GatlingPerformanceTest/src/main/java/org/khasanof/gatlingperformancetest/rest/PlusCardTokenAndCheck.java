package org.khasanof.gatlingperformancetest.rest;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;

// required for Gatling HTTP DSL
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.rest
 * @since 8/27/2023 5:33 PM
 */
public class PlusCardTokenAndCheck extends Simulation {

    public PlusCardTokenAndCheck() {
        setUp(getWsToken().injectOpen(postEndpointInjectionProfile())
        ).protocols(setupProtocolForSimulation());
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return http.baseUrl("https://auth.b1nk.uz")
                .acceptHeader("application/json")
                .userAgentHeader("Gatling/Performance Test");
    }

    public ScenarioBuilder getWsToken() {
        return scenario("token")
                .exec(session -> {
                    http("get token")
                            .post("/realms/hamyon-business/protocol/openid-connect/token")
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .header("Host", "auth.b1nk.uz")
                            .header("Origin", "https://plus.b1nk.uz")
                            .header("Referer", "https://plus.b1nk.uz/")
                            .body(RawFileBody("data/token.json"))
                            .check(
                                    status().is(200),
                                    jsonPath("$.access_token").exists().saveAs("access_token")
                            );
                    return session;
                });
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
