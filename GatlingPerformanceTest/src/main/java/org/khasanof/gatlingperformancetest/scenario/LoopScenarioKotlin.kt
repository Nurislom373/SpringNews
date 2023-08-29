package org.khasanof.gatlingperformancetest.scenario

import io.gatling.javaapi.core.ChainBuilder
import io.gatling.javaapi.core.CoreDsl
import io.gatling.javaapi.core.CoreDsl.exec
import io.gatling.javaapi.core.Session
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration
import java.util.*


class LoopScenarioKotlin : Simulation() {

    private fun paceExample(): ChainBuilder? {
        return exec(
                CoreDsl.forever().on(
                        CoreDsl.pace(5)
                                .exec { session: Session? ->
                                    http("get regions")["/api/regions"]
                                            .header("Content-Type", "application/json")
                                            .check(status().`is`(200))
                                    CoreDsl.pause(1, 4)
                                    session
                                }))
    }

    private fun repeatExample(): ChainBuilder? {
        return exec( // with an Int times
                CoreDsl.repeat(5)
                        .on(exec(http("regions")["/api/regions"])),  // with a Gatling EL string resolving an Int
                CoreDsl.repeat("#{times}")
                        .on(exec(http("regions")["/api/regions"])),  // with a function times
                CoreDsl.repeat { 5 }
                        .on(exec(http("regions")["/api/regions"])),  // with a counter name
                CoreDsl.repeat(5, "counter")
                        .on(exec { session: Session ->
                            println(session.getInt("counter"))
                            session
                        })
        )
    }

    private fun foreachExample(): ChainBuilder {
        return exec( // with a static List
                CoreDsl.foreach(mutableListOf<String?>("elt1", "elt2"), "elt")
                        .on(exec { session: Session? ->
                            http("regions")["/api/regions"]
                                    .check(status().`is`(200))
                            session
                        }),  // with a Gatling EL string
                CoreDsl.foreach("#{elts}", "elt")
                        .on(exec { session: Session ->
                            http("region")["/api/region/{id}"]
                                    .formParam("id", session.getString("elt")!!)
                                    .check(status().`is`(200))
                            session
                        }),  // with a function
                CoreDsl.foreach({ mutableListOf("elt1", "elt2") }, "elt")
                        .on(exec { session: Session? ->
                            http("regions")["/api/regions"]
                                    .check(status().`is`(200))
                            session
                        }),  // with a counter name
                CoreDsl.foreach(mutableListOf<String?>("elt1", "elt2"), "elt", "counter")
                        .on(exec { session: Session ->
                            http("regions")["/api/regions"]
                                    .formParam("id", session.getString("elt2")!!)
                                    .check(status().`is`(200))
                            session
                        })
        )
    }

    private fun duringExample(): ChainBuilder? {
        return exec( // with a duration in seconds
                CoreDsl.during(5)
                        .on(exec(http("regions")["/api/regions"])),  // with a java.time.Duration
                CoreDsl.during(Duration.ofMinutes(10))
                        .on(exec(http("regions")["/api/regions"])),  // with a Gatling EL string resolving a duration
                CoreDsl.during("#{times}")
                        .on(exec(http("regions")["/api/regions"])),  // with a function times
                CoreDsl.during { session: Session? -> Duration.ofMinutes(10) }
                        .on(exec(http("regions")["/api/regions"])),  // with a counter name
                CoreDsl.during(5, "counter")
                        .on(exec(http("regions")["/api/regions"])),  // with exitASAP
                CoreDsl.during(5, "counter", false)
                        .on(exec(http("regions")["/api/regions"]))
        )
    }

    private fun asLongAsExample(): ChainBuilder {
        return exec( // with a Gatling EL string resolving to a boolean
                CoreDsl.asLongAs("#{condition}")
                        .on(exec(http("regions")["/api/regions"])),  // with a function
                CoreDsl.asLongAs { session: Session -> session.getBoolean("condition") }
                        .on(exec(http("regions")["/api/regions"])),  // with a counter name and exitASAP
                CoreDsl.asLongAs("#{condition}", "counter", false)
                        .on(exec(http("regions")["/api/regions"]))
        )
    }

    private fun doWhileExample(): ChainBuilder {
        return exec( // with a Gatling EL string resolving to a boolean
                CoreDsl.doWhile("#{condition}").on(
                        exec(http("name")["/"])
                ),  // with a function
                CoreDsl.doWhile { session: Session -> session.getBoolean("condition") }.on(
                        exec(http("name")["/"])
                ),  // with a counter name
                CoreDsl.doWhile("#{condition}", "counter").on(
                        exec(http("name")["/"])
                )
        )
    }

    private fun asLongAsDuringExample(): ChainBuilder {
        return CoreDsl.asLongAsDuring("#{condition}", 5)
                .on(exec(http("name")["/"])
                        .exec( // with a Gatling EL string resolving to a boolean and an int duration
                                CoreDsl.asLongAsDuring("#{condition}", 5).on(
                                        exec(http("name")["/"])),  // with a counter name and exitASAP
                                CoreDsl.asLongAsDuring({ session: Session? -> true },
                                        Duration.ofMinutes(10), "counter", false).on(
                                        exec(http("name")["/"]))
                        ))
    }

    private fun doWhileDuringExample(): ChainBuilder {
        return CoreDsl.doWhileDuring("#{condition}", 5)
                .on(
                        exec(http("name")["/"])
                )
                .exec( // with a Gatling EL string resolving to a boolean and an int duration
                        CoreDsl.doWhileDuring("#{condition}", 5).on(
                                exec(http("name")["/"])
                        ),  // with a counter name and exitASAP
                        CoreDsl.doWhileDuring({ true }, Duration.ofMinutes(10), "counter", false).on(
                                exec(http("name")["/"])
                        )
                )
    }

    private fun foreverExample(): ChainBuilder {
        return CoreDsl.forever()
                .on(
                        exec(http("name")["/"])
                )
                .forever("counter")
                .on(
                        exec(http("name")["/"])
                )
    }

}
