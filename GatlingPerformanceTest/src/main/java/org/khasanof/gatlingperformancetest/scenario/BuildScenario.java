package org.khasanof.gatlingperformancetest.scenario;

import io.gatling.javaapi.core.Simulation;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.scenario
 * @since 8/29/2023 9:02 AM
 */
public class BuildScenario extends Simulation {

    private ScenarioBuilder simpleScenarioBuilder() {
        return scenario("simple")
                .exec(
                        http("get categories")
                                .get("/api/categories")
                                .header("Content-Type", "application/json")
                                .check(status().is(200))
                ).exec(chainBuilder(), subChainBuilder());
    }

    // simple builder
    private ChainBuilder chainBuilder() {
        return exec(actionBuilder());
    }

    // chain builder example
    private ChainBuilder subChainBuilder() {
        return exec(exec(actionBuilder()), exec(actionBuilder()));
    }

    private HttpRequestActionBuilder actionBuilder() {
        return http("get regions")
                .get("/api/regions")
                .header("Content-Type", "application/json")
                .check(status().is(200));
    }

}
