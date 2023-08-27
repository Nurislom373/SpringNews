package org.khasanof.gatlingperformancetest.rest;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

// required for Gatling HTTP DSL
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.http.HttpDsl.*;

// can be omitted if you don't use jdbcFeeder
import io.gatling.javaapi.jdbc.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

// used for specifying durations with a unit, eg Duration.ofMinutes(5)
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.rest
 * @since 8/27/2023 11:59 AM
 */
public class SimpleSimulation extends Simulation {

    private final ScenarioBuilder simple1 = scenario("simple1");
    private final ScenarioBuilder simple2 = scenario("simple2");

    {
        // simple configured globally
        setUp(
                simple1.injectOpen(atOnceUsers(1))
                        .protocols(setupProtocolForSimulation()),
                simple2.injectOpen(atOnceUsers(1))
        ).protocols(setupProtocolForSimulation())
                .assertions(global().failedRequests().count().is(0L));

        // throttling profile configured globally
        setUp(simple1.injectOpen(constantUsersPerSec(100).during(60)))
                .throttle(
                        reachRps(100).in(10),
                        holdFor(Duration.of(1, ChronoUnit.MINUTES)),
                        jumpToRps(50),
                        holdFor(Duration.of(2, ChronoUnit.HOURS))
                );

        // different throttling profiles configured globally
        setUp(
                simple1.injectOpen(atOnceUsers(1))
                        .throttle(reachRps(100).in(10)),
                simple2.injectOpen(atOnceUsers(1))
                        .throttle(reachRps(20).in(10))
        );
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return http.baseUrl("http://localhost:8080")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Gatling/Performance Test");
    }


}
