package org.khasanof.gatlingperformancetest.simulation

import io.gatling.javaapi.core.CoreDsl
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import java.time.Duration
import io.gatling.javaapi.core.CoreDsl.*

class ThrottleKotlinConfig : Simulation() {

    private val scn: ScenarioBuilder = scenario("test")

    init {
        // throttling profile configured globally
        setUp(scn.injectOpen(CoreDsl.constantUsersPerSec(100.0).during(Duration.ofMinutes(30))))
                .throttle(
                        CoreDsl.reachRps(100).`in`(10),
                        CoreDsl.holdFor(Duration.ofMinutes(1)),
                        CoreDsl.jumpToRps(50),
                        CoreDsl.holdFor(Duration.ofHours(2))
                )

        // different throttling profiles configured globally
        setUp(
                scn.injectOpen(CoreDsl.atOnceUsers(1))
                        .throttle(CoreDsl.reachRps(100).`in`(10)),
                scn.injectOpen(CoreDsl.atOnceUsers(1))
                        .throttle(CoreDsl.reachRps(20).`in`(10))
        )
    }

}
