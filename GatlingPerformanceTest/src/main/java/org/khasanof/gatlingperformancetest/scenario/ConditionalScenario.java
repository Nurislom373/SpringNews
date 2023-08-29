package org.khasanof.gatlingperformancetest.scenario;

import io.gatling.javaapi.core.Simulation;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.scenario
 * @since 8/29/2023 9:13 AM
 */
public class ConditionalScenario extends Simulation {

    private ChainBuilder doIfExample() {
        return exec(
                http("get token")
                        .get("/api/token"))
                .doIf("#{condition}")
                .then(exec(http("get session")
                        .get("/api/session"))
                )
                .doIf(session -> session.getBoolean("condition")).then(
                        exec(http("name").get("/"))
                ).exec(
                        // with a Gatling EL string resolving to a boolean
                        doIf("#{condition}").then(
                                exec(http("name").get("/"))
                        ),

                        // with a function
                        doIf(session -> session.getBoolean("condition")).then(
                                exec(http("name").get("/"))
                        )
                );
    }

    private ChainBuilder doIfEqualsExample() {
        return exec(
                doIfEquals("#{actual}", "expectedValue").then(
                        // executed if the session value stored in "actual" is equal to "expectedValue"
                        exec(http("name").get("/"))
                )
        );
    }

}
