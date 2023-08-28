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
 * @since 8/28/2023 11:54 AM
 */
public class HttpProtocolConfig extends Simulation {

    private final ScenarioBuilder scn = scenario("test");

    {
        // pause configuration configured globally
        setUp(scn.injectOpen(atOnceUsers(1)))
                // disable the pauses for the simulation
                .disablePauses()
                // the duration of each pause is what's specified
                // in the `pause(duration)` element.
                .constantPauses()
                // make pauses follow a uniform distribution
                // where the mean is the value specified in the `pause(duration)` element.
                .uniformPauses(0.5)
                .uniformPauses(Duration.ofSeconds(2))
                // make pauses follow an exponential distribution
                // where the mean is the value specified in the `pause(duration)` element.
                .exponentialPauses()
                // the pause duration is computed by the provided function (in milliseconds).
                // In this case the filled duration is bypassed.
                .customPauses(session -> 5L);

        // different pause configurations configured on each population
        setUp(
                scn.injectOpen(atOnceUsers(1))
                        .disablePauses(),
                scn.injectOpen(atOnceUsers(1))
                        .exponentialPauses()
        );

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
