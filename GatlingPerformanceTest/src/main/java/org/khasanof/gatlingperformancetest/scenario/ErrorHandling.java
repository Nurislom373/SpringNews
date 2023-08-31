package org.khasanof.gatlingperformancetest.scenario;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.http.*;
import net.sf.saxon.om.Chain;

import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.scenario
 * @since 8/31/2023 8:29 PM
 */
public class ErrorHandling {

    private ChainBuilder tryMaxExample() {
        return exec(
                tryMax(5).on(
                        exec(http("name").get("/"))
                ),
                tryMax(5, "counter").on(
                        exec(http("name").get("/"))
                )
        ).tryMax(5, "counter").on(
                exec(http("name").get("/"))
        );
    }

    private ChainBuilder exitBlockOnFailExample() {
        return exec(
                exitBlockOnFail(
                        exec(http("name").get("/"))
                )
        ).exitBlockOnFail(
                exec(http("name").get("/"))
        );
    }

}
