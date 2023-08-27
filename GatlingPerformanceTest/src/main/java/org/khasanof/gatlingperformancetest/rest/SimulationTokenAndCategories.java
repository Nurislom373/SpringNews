package org.khasanof.gatlingperformancetest.rest;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

// required for Gatling HTTP DSL
import static io.gatling.javaapi.http.HttpDsl.*;

// can be omitted if you don't use jdbcFeeder


/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.rest
 * @since 8/27/2023 12:33 PM
 */
public class SimulationTokenAndCategories {

    private final String AUTH = "Authorization";

    public ScenarioBuilder tokenAndCategories() {
        return scenario("get token")
                .exec(session -> {
                    http("get token")
                            .post("/auth/realms/salary/protocol/openid-connect/token")
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .check(
                                    status().is(200),
                                    jsonPath("$.access_token").exists().saveAs("access_token")
                            );
                    return session;
                })
                .pause(1)
                .exec(session -> {
                    http("get categories")
                            .get("/api/categories")
                            .header(AUTH, "bearer #{access_token}")
                            .check(status().is(200));
                    return session;
                });
    }

}
