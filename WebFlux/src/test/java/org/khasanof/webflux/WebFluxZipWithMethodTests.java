package org.khasanof.webflux;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.util.LoggerUtils;
import reactor.util.function.Tuple2;

import java.util.logging.Level;

import static org.assertj.core.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 13.05.2023
 * <br/>
 * Time: 13:43
 * <br/>
 * Package: org.khasanof.webflux
 */
public class WebFluxZipWithMethodTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void fluxZipSimpleTest() {
        Flux<String> f1 = Flux.just("f1");
        Flux<String> f2 = Flux.just("f2");
        Flux<String> f3 = Flux.just("f3");

        StepVerifier.create(Flux.zip(f1, f2, f3))
                .assertNext(res -> {
                    assertThat(res.getT1()).isEqualTo("f1");
                    assertThat(res.getT2()).isEqualTo("f2");
                    assertThat(res.getT3()).isEqualTo("f3");
                })
                .verifyComplete();
    }

    @Test
    void fluxZipSameLength() {
        Flux<String> f1 = Flux.just("f11", "f12");
        Flux<String> f2 = Flux.just("f21", "f22");
        Flux<String> f3 = Flux.just("f31", "f32");

        Flux<String> flux = Flux.zip(f1, f2, f3)
                .log("zip f1, f2 and f3 Fluxs", Level.INFO)
                .map(x -> {
                    String result = x.getT1() + "," + x.getT2() + "," + x.getT3();
                    logger.info("result is zipped : {}", result);
                    return result;
                });

        StepVerifier.create(flux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void zipFluxes() {
        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa");

        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples");

        Flux<Tuple2<String, String>> zippedFlux =
                Flux.zip(characterFlux, foodFlux);

        StepVerifier.create(zippedFlux)
                .expectNextMatches(p ->
                        p.getT1().equals("Garfield") &&
                                p.getT2().equals("Lasagna"))
                .expectNextMatches(p ->
                        p.getT1().equals("Kojak") &&
                                p.getT2().equals("Lollipops"))
                .expectNextMatches(p ->
                        p.getT1().equals("Barbossa") &&
                                p.getT2().equals("Apples"))
                .verifyComplete();
    }

}
