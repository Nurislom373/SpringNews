package org.khasanof.gatlingperformancetest;

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
 * @see org.khasanof.gatlingperformancetest
 * @since 8/27/2023 7:46 PM
 */
public class InjectionExamples extends Simulation {

    private final ScenarioBuilder scn = scenario("test");

    {
        setUp(
                // Open Model
                scn.injectOpen(
                        nothingFor(4),
                        atOnceUsers(10),
                        rampUsers(10).during(5),
                        constantUsersPerSec(20).during(15),
                        constantUsersPerSec(20).during(15).randomized(),
                        rampUsersPerSec(10).to(20).during(10),
                        rampUsersPerSec(10).to(20).during(10).randomized(),
                        stressPeakUsers(1000).during(20)
                ),

                // Closed Model
                scn.injectClosed(
                        constantConcurrentUsers(10).during(10),
                        rampConcurrentUsers(10).to(20).during(10)
                ),

                // generate an open workload injection profile
                // with levels of 10, 15, 20, 25 and 30 arriving users per second
                // each level lasting 10 seconds
                // separated by linear ramps lasting 10 seconds
                scn.injectOpen(
                        incrementUsersPerSec(5.0)
                                .times(5)
                                .eachLevelLasting(10)
                                .separatedByRampsLasting(10)
                                .startingFrom(10) // Double
                ),

                // generate a closed workload injection profile
                // with levels of 10, 15, 20, 25 and 30 concurrent users
                // each level lasting 10 seconds
                // separated by linear ramps lasting 10 seconds
                scn.injectClosed(
                        incrementConcurrentUsers(5)
                                .times(5)
                                .eachLevelLasting(10)
                                .separatedByRampsLasting(10)
                                .startingFrom(10)),

                scn.injectOpen(injectionStep()),

                scn.injectOpen(injectionStep())
                        .andThen(scn.injectOpen(injectionStep())
                                .andThen(scn.injectOpen(injectionStep()))
                        ).andThen(scn.injectOpen(injectionStep()))
        );
    }

    private OpenInjectionStep.RampRate.RampRateOpenInjectionStep injectionStep() {
        return CoreDsl.rampUsersPerSec(10)
                .to(100)
                .during(Duration.ofSeconds(10));
    }

}
