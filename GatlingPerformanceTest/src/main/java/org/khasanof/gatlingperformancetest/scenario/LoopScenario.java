package org.khasanof.gatlingperformancetest.scenario;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.Simulation;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.scenario
 * @since 8/28/2023 3:42 PM
 */
public class LoopScenario extends Simulation {

    private ChainBuilder paceExample() {
        return exec(
                forever().on(
                        pace(5)
                                .exec(session -> {
                                    http("get regions")
                                            .get("/api/regions")
                                            .header("Content-Type", "application/json")
                                            .check(status().is(200));
                                    pause(1, 4);
                                    return session;
                                })));
    }

    private ChainBuilder repeatExample() {
        return exec(
                // with an Int times
                repeat(5)
                        .on(exec(http("regions").get("/api/regions"))),

                // with a Gatling EL string resolving an Int
                repeat("#{times}")
                        .on(exec(http("regions").get("/api/regions"))),

                // with a function times
                repeat(session -> 5)
                        .on(exec(http("regions").get("/api/regions"))),

                // with a counter name
                repeat(5, "counter")
                        .on(exec(session -> {
                            System.out.println(session.getInt("counter"));
                            return session;
                        }))
        );
    }

    private ChainBuilder foreachExample() {
        return exec(
                // with a static List
                foreach(Arrays.asList("elt1", "elt2"), "elt")
                        .on(exec(session -> {
                            http("regions")
                                    .get("/api/regions")
                                    .check(status().is(200));
                            return session;
                        })),

                // with a Gatling EL string
                foreach("#{elts}", "elt")
                        .on(exec(session -> {
                            http("region")
                                    .get("/api/region/{id}")
                                    .formParam("id", Objects.requireNonNull(session.getString("elt")))
                                    .check(status().is(200));
                            return session;
                        })),

                // with a function
                foreach(session -> Arrays.asList("elt1", "elt2"), "elt")
                        .on(exec(session -> {
                            http("regions")
                                    .get("/api/regions")
                                    .check(status().is(200));
                            return session;
                        })),

                // with a counter name
                foreach(Arrays.asList("elt1", "elt2"), "elt", "counter")
                        .on(exec(session -> {
                            http("regions")
                                    .get("/api/regions")
                                    .formParam("id", Objects.requireNonNull(session.getString("elt2")))
                                    .check(status().is(200));
                            return session;
                        }))
        );
    }

    private ChainBuilder duringExample() {
        return exec(
                // with a duration in seconds
                during(5)
                        .on(exec(http("regions").get("/api/regions"))),

                // with a java.time.Duration
                during(Duration.ofMinutes(10))
                        .on(exec(http("regions").get("/api/regions"))),

                // with a Gatling EL string resolving a duration
                during("#{times}")
                        .on(exec(http("regions").get("/api/regions"))),

                // with a function times
                during(session -> Duration.ofMinutes(10))
                        .on(exec(http("regions").get("/api/regions"))),

                // with a counter name
                during(5, "counter")
                        .on(exec(http("regions").get("/api/regions"))),

                // with exitASAP
                during(5, "counter", false)
                        .on(exec(http("regions").get("/api/regions")))
        );
    }

    private ChainBuilder asLongAsExample() {
        return exec(

                // with a Gatling EL string resolving to a boolean
                asLongAs("#{condition}")
                        .on(exec(http("regions").get("/api/regions"))),

                // with a function
                asLongAs(session -> session.getBoolean("condition"))
                        .on(exec(http("regions").get("/api/regions"))),

                // with a counter name and exitASAP
                asLongAs("#{condition}", "counter", false)
                        .on(exec(http("regions").get("/api/regions")))
        );
    }

    private ChainBuilder doWhileExample() {
        return exec(
                // with a Gatling EL string resolving to a boolean
                doWhile("#{condition}").on(
                        exec(http("name").get("/"))
                ),

                // with a function
                doWhile(session -> session.getBoolean("condition")).on(
                        exec(http("name").get("/"))
                ),

                // with a counter name
                doWhile("#{condition}", "counter").on(
                        exec(http("name").get("/"))
                )
        );
    }

    private ChainBuilder asLongAsDuringExample() {
        return asLongAsDuring("#{condition}", 5)
                .on(exec(http("name").get("/"))
                        .exec(
                                // with a Gatling EL string resolving to a boolean and an int duration
                                asLongAsDuring("#{condition}", 5).on(
                                        exec(http("name").get("/"))),

                                // with a counter name and exitASAP
                                asLongAsDuring(session -> true,
                                        Duration.ofMinutes(10), "counter", false).on(
                                        exec(http("name").get("/")))
                        ));
    }

    private ChainBuilder doWhileDuringExample() {
        return doWhileDuring("#{condition}", 5)
                .on(
                        exec(http("name").get("/"))
                )
                .exec(
                        // with a Gatling EL string resolving to a boolean and an int duration
                        doWhileDuring("#{condition}", 5).on(
                                exec(http("name").get("/"))
                        ),
                        // with a counter name and exitASAP
                        doWhileDuring(session -> true, Duration.ofMinutes(10), "counter", false).on(
                                exec(http("name").get("/"))
                        )
                );
    }

    private ChainBuilder foreverExample() {
        return forever()
                .on(
                        exec(http("name").get("/"))
                )
                .forever("counter")
                .on(
                        exec(http("name").get("/"))
                );
    }


}
