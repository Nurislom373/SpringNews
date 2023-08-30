package org.khasanof.gatlingperformancetest.scenario;

import io.gatling.javaapi.core.Simulation;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.http.*;
import net.sf.saxon.om.Chain;

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

    private ChainBuilder doIfOrElseExample() {
        return exec(
                doIfOrElse("#{condition}").then(
                        exec(http("name").get("/"))
                ).orElse(
                        exec(http("else").get("/"))
                )
        ).doIfOrElse("#{condition}").then(
                exec(http("name").get("/"))
        ).orElse(
                exec(http("else").get("/"))
        );
    }

    private ChainBuilder doIfEqualsOrElseExample() {
        return exec(
                doIfEqualsOrElse("#{actual}", "expectedValue").then(
                        // executed if the session value stored in "actual" equals to "expectedValue"
                        exec(http("name").get("/"))
                ).orElse(
                        // executed if the session value stored in "actual" is not equal to "expectedValue"
                        exec(http("else").get("/"))
                )
        ).doIfEqualsOrElse("#{actual}", "expectedValue").then(
                // executed if the session value stored in "actual" equals to "expectedValue"
                exec(http("name").get("/"))
        ).orElse(
                // executed if the session value stored in "actual" is not equal to "expectedValue"
                exec(http("else").get("/"))
        );
    }

    private ChainBuilder doSwitchExample() {
        return exec(
                doSwitch("#{myKey}").on(
                        Choice.withKey("foo", exec(http("name1").get("/foo"))),
                        Choice.withKey("bar", exec(http("name2").get("/bar")))
                )
        ).doSwitch("#{myKey}").on(
                Choice.withKey("foo", exec(http("name1").get("/foo"))),
                Choice.withKey("bar", exec(http("name2").get("/bar")))
        );
    }

    private ChainBuilder doSwitchOrElseExample() {
        return exec(
                doSwitchOrElse("#{myKey}").on(
                        Choice.withKey("foo", exec(http("name1").get("/foo"))),
                        Choice.withKey("bar", exec(http("name2").get("/bar")))
                ).orElse(
                        exec(http("name3").get("/baz"))
                )
        ).doSwitchOrElse("#{myKey}").on(
                Choice.withKey("foo", exec(http("name1").get("/foo"))),
                Choice.withKey("bar", exec(http("name2").get("/bar")))
        ).orElse(
                exec(http("name3").get("/baz"))
        );
    }

    private ChainBuilder randomSwitchExample() {
        return exec(
                randomSwitch().on(
                        Choice.withWeight(60.0, exec(http("name1").get("/foo"))),
                        Choice.withWeight(40.0, exec(http("name2").get("/bar")))
                )
        ).randomSwitch().on(
                Choice.withWeight(60.0, exec(http("name1").get("/foo"))),
                Choice.withWeight(40.0, exec(http("name2").get("/bar")))
        );
    }

    private ChainBuilder randomSwitchOrElseExample() {
        return exec(
                randomSwitchOrElse().on(
                        Choice.withWeight(60.0, exec(http("name1").get("/foo"))),
                        Choice.withWeight(20.0, exec(http("name2").get("/bar")))
                ).orElse(
                        exec(http("name3").get("/baz"))
                )
        ).randomSwitchOrElse().on(
                Choice.withWeight(60.0, exec(http("name1").get("/foo"))),
                Choice.withWeight(20.0, exec(http("name2").get("/bar")))
        ).orElse(
                exec(http("name3").get("/baz"))
        );
    }

}
