package org.khasanof.gatlingperformancetest.simulation

import io.gatling.javaapi.core.CoreDsl
import io.gatling.javaapi.core.Simulation
import java.time.Duration

class HttpProtocolKotlinConfig : Simulation() {

    private val scn = CoreDsl.scenario("test")

    init {

        // pause configuration configured globally
        setUp(scn.injectOpen(CoreDsl.atOnceUsers(1))) // disable the pauses for the simulation
                .disablePauses() // the duration of each pause is what's specified
                // in the `pause(duration)` element.
                .constantPauses() // make pauses follow a uniform distribution
                // where the mean is the value specified in the `pause(duration)` element.
                .uniformPauses(0.5)
                .uniformPauses(Duration.ofSeconds(2)) // make pauses follow an exponential distribution
                // where the mean is the value specified in the `pause(duration)` element.
                .exponentialPauses() // the pause duration is computed by the provided function (in milliseconds).
                // In this case the filled duration is bypassed.
                .customPauses { 5L }

        // different pause configurations configured on each population
        setUp(
                scn.injectOpen(CoreDsl.atOnceUsers(1))
                        .disablePauses(),
                scn.injectOpen(CoreDsl.atOnceUsers(1))
                        .exponentialPauses()
        )

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
