package org.khasanof.gatlingperformancetest.scenario

import io.gatling.javaapi.core.*
import io.gatling.javaapi.http.HttpDsl

class ConditionalScenarioKotlin : Simulation() {

    fun doIfExample(): ChainBuilder {
        return CoreDsl.exec(
                HttpDsl.http("get token")["/api/token"])
                .doIf("#{condition}")
                .then(CoreDsl.exec(HttpDsl.http("get session")["/api/session"])
                )
                .doIf { session: Session -> session.getBoolean("condition") }.then(
                        CoreDsl.exec(HttpDsl.http("name")["/"])
                ).exec( // with a Gatling EL string resolving to a boolean
                        CoreDsl.doIf("#{condition}").then(
                                CoreDsl.exec(HttpDsl.http("name")["/"])
                        ),  // with a function
                        CoreDsl.doIf { session: Session -> session.getBoolean("condition") }.then(
                                CoreDsl.exec(HttpDsl.http("name")["/"])
                        )
                )
    }

    fun doIfEqualsExample(): ChainBuilder {
        return CoreDsl.exec(
                CoreDsl.doIfEquals("#{actual}", "expectedValue").then( // executed if the session value stored in "actual" is equal to "expectedValue"
                        CoreDsl.exec(HttpDsl.http("name")["/"])
                )
        )
    }

    private fun doIfOrElseExample(): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.doIfOrElse("#{condition}").then(
                CoreDsl.exec(HttpDsl.http("name")["/"])
            ).orElse(
                CoreDsl.exec(HttpDsl.http("else")["/"])
            )
        ).doIfOrElse("#{condition}").then(
            CoreDsl.exec(HttpDsl.http("name")["/"])
        ).orElse(
            CoreDsl.exec(HttpDsl.http("else")["/"])
        )
    }

    private fun doIfEqualsOrElseExample(): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.doIfEqualsOrElse("#{actual}", "expectedValue")
                .then( // executed if the session value stored in "actual" equals to "expectedValue"
                    CoreDsl.exec(HttpDsl.http("name")["/"])
                ).orElse( // executed if the session value stored in "actual" is not equal to "expectedValue"
                    CoreDsl.exec(HttpDsl.http("else")["/"])
                )
        ).doIfEqualsOrElse("#{actual}", "expectedValue")
            .then( // executed if the session value stored in "actual" equals to "expectedValue"
                CoreDsl.exec(HttpDsl.http("name")["/"])
            ).orElse( // executed if the session value stored in "actual" is not equal to "expectedValue"
                CoreDsl.exec(HttpDsl.http("else")["/"])
            )
    }

    private fun doSwitchExample(): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.doSwitch("#{myKey}").on(
                Choice.withKey("foo", CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
                Choice.withKey("bar", CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
            )
        ).doSwitch("#{myKey}").on(
            Choice.withKey("foo", CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
            Choice.withKey("bar", CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
        )
    }

    private fun doSwitchOrElseExample(): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.doSwitchOrElse("#{myKey}").on(
                Choice.withKey("foo", CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
                Choice.withKey("bar", CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
            ).orElse(
                CoreDsl.exec(HttpDsl.http("name3")["/baz"])
            )
        ).doSwitchOrElse("#{myKey}").on(
            Choice.withKey("foo", CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
            Choice.withKey("bar", CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
        ).orElse(
            CoreDsl.exec(HttpDsl.http("name3")["/baz"])
        )
    }

    private fun randomSwitchExample(): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.randomSwitch().on(
                Choice.withWeight(60.0, CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
                Choice.withWeight(40.0, CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
            )
        ).randomSwitch().on(
            Choice.withWeight(60.0, CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
            Choice.withWeight(40.0, CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
        )
    }

    private fun randomSwitchOrElseExample(): ChainBuilder {
        return CoreDsl.exec(
            CoreDsl.randomSwitchOrElse().on(
                Choice.withWeight(60.0, CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
                Choice.withWeight(20.0, CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
            ).orElse(
                CoreDsl.exec(HttpDsl.http("name3")["/baz"])
            )
        ).randomSwitchOrElse().on(
            Choice.withWeight(60.0, CoreDsl.exec(HttpDsl.http("name1")["/foo"])),
            Choice.withWeight(20.0, CoreDsl.exec(HttpDsl.http("name2")["/bar"]))
        ).orElse(
            CoreDsl.exec(HttpDsl.http("name3")["/baz"])
        )
    }

}
