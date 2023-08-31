package org.khasanof.gatlingperformancetest.scenario

import io.gatling.javaapi.core.ChainBuilder
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.http


class ErrorHandlingKt {

    private fun tryMaxExample(): ChainBuilder {
        return exec(
            tryMax(5).on(
                exec(http("name")["/"])
            ),
            tryMax(5, "counter").on(
                exec(http("name")["/"])
            )
        ).tryMax(5, "counter").on(
            exec(http("name")["/"])
        )
    }

    private fun exitBlockOnFailExample(): ChainBuilder {
        return exec(
            exitBlockOnFail(
                exec(http("name")["/"])
            )
        ).exitBlockOnFail(
            exec(http("name")["/"])
        )
    }

}
