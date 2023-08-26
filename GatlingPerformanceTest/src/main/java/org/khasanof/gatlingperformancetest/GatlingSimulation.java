package org.khasanof.gatlingperformancetest;

import com.github.javafaker.Faker;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest
 * @since 8/24/2023 6:03 PM
 */
public class GatlingSimulation extends Simulation {

    private static final String stringBody = "{ \"firstName\": \"${firstName}\", \"lastName\": \"${lastName}\", \"company\": \"${company}\" }";
    private static final HttpProtocolBuilder HTTP_PROTOCOL_BUILDER = setupProtocolForSimulation();
    private static final Iterator<Map<String, Object>> FEED_DATA = feedData();
    private static final ScenarioBuilder POST_SCENARIO_BUILDER = buildPostScenario();

    {
        setUp(POST_SCENARIO_BUILDER.injectOpen(postEndpointInjectionProfile())
                .protocols(HTTP_PROTOCOL_BUILDER))
                .assertions(
                        CoreDsl.global().responseTime().max().lte(10000),
                        CoreDsl.global().successfulRequests().percent().gt(90d)
                );
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return http.baseUrl("http://localhost:8080")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Gatling/Performance Test");
    }

    private static Iterator<Map<String, Object>> feedData() {
        Iterator<Map<String, Object>> iterator;
        Faker faker = new Faker();
        iterator = Stream.generate(() -> {
                    Map<String, Object> stringObjectMap = new HashMap<>();
                    stringObjectMap.put("firstName", faker.name().firstName());
                    stringObjectMap.put("lastName", faker.name().lastName());
                    stringObjectMap.put("company", faker.company().name());
                    return stringObjectMap;
                })
                .iterator();
        return iterator;
    }

    private static ScenarioBuilder buildPostScenario() {
        return CoreDsl.scenario("Load Test By Test Controller")
                .feed(FEED_DATA)
                .exec(http("create-employee-request")
                        .post("/api/create-fake-data")
                        .header("Content-Type", "application/json")
                        .body(StringBody(stringBody))
                        .check(HttpDsl.status().is(201)))
                .pause(2)
                .exec(http("get-employee-request")
                        .get("/api/get-fake-data")
                        .header("Content-Type", "application/json")
                        .check(HttpDsl.status().is(200)))
                .pause(2, 3);
    }

    private OpenInjectionStep.RampRate.RampRateOpenInjectionStep postEndpointInjectionProfile() {

        int totalDesiredUserCount = 200;
        double userRampUpPerInterval = 50;
        double rampUpIntervalSeconds = 30;
        int totalRampUptimeSeconds = 20;
        int steadyStateDurationSeconds = 10;

        return CoreDsl.rampUsersPerSec(userRampUpPerInterval / (rampUpIntervalSeconds / 60))
                .to(totalDesiredUserCount)
                .during(Duration.ofSeconds(totalRampUptimeSeconds + steadyStateDurationSeconds));
    }

}
