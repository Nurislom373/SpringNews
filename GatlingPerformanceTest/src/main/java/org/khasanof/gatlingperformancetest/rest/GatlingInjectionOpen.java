package org.khasanof.gatlingperformancetest.rest;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.rest
 * @since 8/26/2023 4:22 PM
 */
public class GatlingInjectionOpen extends Simulation {

    private final HttpRequestActionBuilder countries = CountrySimulations.countriesActions();

    public GatlingInjectionOpen() {
        setUp(
                scenarioBuilder().injectOpen(OpenInjectionStep.atOnceUsers(2000)),
                scenarioBuilder().injectOpen(OpenInjectionStep.nothingFor(
                        Duration.of(60, ChronoUnit.SECONDS)), CoreDsl.rampUsers(5).during(400)),
                scenarioBuilder().injectOpen(CoreDsl.rampUsers(500).during(200))
        );
    }

    private ScenarioBuilder scenarioBuilder() {
        return CoreDsl.scenario("Countries Scenario")
                .exec(countries);
    }

}
