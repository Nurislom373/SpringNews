package org.khasanof.gatlingperformancetest.rest;

import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest
 * @since 8/26/2023 10:25 AM
 */
public abstract class CountrySimulations {

    public static HttpRequestActionBuilder countriesActions() {
        return http("get countries http")
                .get("/api/countries")
                .header("Content-Type", "application/json")
                .check(HttpDsl.status().is(200));
    }

}
