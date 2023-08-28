package org.khasanof.gatlingperformancetest.scenario


import io.gatling.javaapi.core.ChainBuilder
import io.gatling.javaapi.core.CoreDsl.exec
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import io.gatling.javaapi.http.HttpRequestActionBuilder


class LoopScenarioKotlin : Simulation() {

    init {
        setUp()
    }

    private fun simpleScenarioBuilder(): ScenarioBuilder {
        return scenario("simple")
                .exec(
                        http("get categories")
                                .get("/api/categories")
                                .header("Content-Type", "application/json")
                                .check(status().`is`(200))
                ).exec { session ->
                    chainBuilder()
                    return@exec session
                }
    }

    // simple builder
    private fun chainBuilder(): ChainBuilder {
        return exec(actionBuilder())
    }

    // chain builder example
    private fun subChainBuilder(): ChainBuilder {
        return exec(exec(actionBuilder()), exec(actionBuilder()))
    }

    private fun actionBuilder(): HttpRequestActionBuilder {
        return http("get regions")
                .get("/api/regions")
                .header("Content-Type", "application/json")
                .check(status().`is`(200))
    }



}
