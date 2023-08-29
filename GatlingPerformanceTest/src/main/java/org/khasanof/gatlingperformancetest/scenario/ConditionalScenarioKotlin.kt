package org.khasanof.gatlingperformancetest.scenario

import io.gatling.javaapi.core.ChainBuilder
import io.gatling.javaapi.core.CoreDsl
import io.gatling.javaapi.core.Session
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl

class ConditionalScenarioKotlin : Simulation() {

    fun doIfExample(): ChainBuilder {
        return CoreDsl.exec(
                HttpDsl.http("get token")["/api/token"])
                .doIf("#{condition}")
                .then(CoreDsl.exec(HttpDsl.http("get session")["/api/session"])
                )
                .doIf { session: Session -> session.getBoolean("condition") }.then(
                        CoreDsl.exec(HttpDsl.http("name")["/"])
                ).exec( // with a Gatling EL string resolving to a boolean
                        CoreDsl.doIf("#{condition}").then(
                                CoreDsl.exec(HttpDsl.http("name")["/"])
                        ),  // with a function
                        CoreDsl.doIf { session: Session -> session.getBoolean("condition") }.then(
                                CoreDsl.exec(HttpDsl.http("name")["/"])
                        )
                )
    }

    fun doIfEqualsExample(): ChainBuilder {
        return CoreDsl.exec(
                CoreDsl.doIfEquals("#{actual}", "expectedValue").then( // executed if the session value stored in "actual" is equal to "expectedValue"
                        CoreDsl.exec(HttpDsl.http("name")["/"])
                )
        )
    }

}
