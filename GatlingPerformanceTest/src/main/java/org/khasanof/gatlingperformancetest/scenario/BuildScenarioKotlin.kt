package org.khasanof.gatlingperformancetest.scenario

import io.gatling.javaapi.core.ChainBuilder
import io.gatling.javaapi.core.CoreDsl
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl
import io.gatling.javaapi.http.HttpRequestActionBuilder

class BuildScenarioKotlin : Simulation() {

    private fun simpleScenarioBuilder(): ScenarioBuilder {
        return CoreDsl.scenario("simple")
                .exec { session ->
                    HttpDsl.http("get categories")["/api/categories"]
                            .header("Content-Type", "application/json")
                            .check(HttpDsl.status().`is`(200))
                    return@exec session
                }.exec(chainBuilder(), subChainBuilder())
    }

    // simple builder
    private fun chainBuilder(): ChainBuilder {
        return CoreDsl.exec(actionBuilder())
    }

    // chain builder example
    private fun subChainBuilder(): ChainBuilder {
        return CoreDsl.exec(CoreDsl.exec(actionBuilder()), CoreDsl.exec(actionBuilder()))
    }

    private fun actionBuilder(): HttpRequestActionBuilder {
        return HttpDsl.http("get regions")["/api/regions"]
                .header("Content-Type", "application/json")
                .check(HttpDsl.status().`is`(200))
    }

}
