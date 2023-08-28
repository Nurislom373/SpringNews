package org.khasanof.gatlingperformancetest.simulation

import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import java.time.Duration
import io.gatling.javaapi.core.CoreDsl.*

class ThrottleKotlinConfig : Simulation() {

    private val scn: ScenarioBuilder = scenario("test")

    init {
        // throttling profile configured globally
        setUp(scn.injectOpen(constantUsersPerSec(100.0).during(Duration.ofMinutes(30))))
                .throttle(
                        reachRps(100).`in`(10),
                        holdFor(Duration.ofMinutes(1)),
                        jumpToRps(50),
                        holdFor(Duration.ofHours(2))
                )

        // different throttling profiles configured globally
        setUp(
                scn.injectOpen(atOnceUsers(1))
                        .throttle(reachRps(100).`in`(10)),
                scn.injectOpen(atOnceUsers(1))
                        .throttle(reachRps(20).`in`(10))
        )
    }

}
