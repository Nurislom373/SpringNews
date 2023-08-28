package org.khasanof.gatlingperformancetest.simulation;

import io.gatling.javaapi.core.Simulation;
// required for Gatling core structure DSL
import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

// required for Gatling HTTP DSL
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.simulation
 * @since 8/28/2023 12:09 PM
 */
public class ThrottleConfig extends Simulation {

    private final ScenarioBuilder scn = scenario("test");

    {
        // throttling profile configured globally
        setUp(scn.injectOpen(constantUsersPerSec(100).during(Duration.ofMinutes(30))))
                .throttle(
                        reachRps(100).in(10),
                        holdFor(Duration.ofMinutes(1)),
                        jumpToRps(50),
                        holdFor(Duration.ofHours(2))
                );

        // different throttling profiles configured globally
        setUp(
                scn.injectOpen(atOnceUsers(1))
                        .throttle(reachRps(100).in(10)),
                scn.injectOpen(atOnceUsers(1))
                        .throttle(reachRps(20).in(10))
        );
    }

}
