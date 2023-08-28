package org.khasanof.gatlingperformancetest

import io.gatling.javaapi.core.OpenInjectionStep
import io.gatling.javaapi.core.Simulation

import io.gatling.javaapi.core.*

import io.gatling.javaapi.core.CoreDsl.*

import io.gatling.javaapi.http.*

import java.time.Duration

import io.gatling.javaapi.http.HttpDsl.*
import java.time.temporal.ChronoUnit

class InjectionKotlinExample : Simulation() {

    private val scn = scenario("test")

    init {
        setUp(
                // Open Model
                scn.injectOpen(
                        nothingFor(4),
                        atOnceUsers(10),
                        rampUsers(10).during(5),
                        constantUsersPerSec(20.0).during(15),
                        constantUsersPerSec(20.0).during(15).randomized(),
                        rampUsersPerSec(10.0).to(20.0).during(10),
                        rampUsersPerSec(10.0).to(20.0).during(10).randomized(),
                        stressPeakUsers(1000).during(20)
                ),

                // Closed Model
                scn.injectClosed(
                        constantConcurrentUsers(10).during(10),
                        rampConcurrentUsers(10).to(20).during(10)
                ),

                // Generate an open workload injection profile
                // with levels of 10, 15, 20, 25, and 30 arriving users per second
                // each level lasting 10 seconds
                // separated by linear ramps lasting 10 seconds
                scn.injectOpen(
                        incrementUsersPerSec(5.0)
                                .times(5)
                                .eachLevelLasting(10)
                                .separatedByRampsLasting(10)
                                .startingFrom(10.0) // Double
                ),

                // Generate a closed workload injection profile
                // with levels of 10, 15, 20, 25, and 30 concurrent users
                // each level lasting 10 seconds
                // separated by linear ramps lasting 10 seconds
                scn.injectClosed(
                        incrementConcurrentUsers(5)
                                .times(5)
                                .eachLevelLasting(10)
                                .separatedByRampsLasting(10)
                                .startingFrom(10)
                ),

                scn.injectOpen(injectionStep()),

                scn.injectOpen(injectionStep())
                        .andThen(scn.injectOpen(injectionStep())
                                .andThen(scn.injectOpen(injectionStep()))
                        ).andThen(scn.injectOpen(injectionStep()))
        )
    }

    private fun injectionStep(): OpenInjectionStep.RampRate.RampRateOpenInjectionStep {
        return rampUsersPerSec(10.0).to(100.0)
                .during(Duration.of(10, ChronoUnit.SECONDS))
    }

}
