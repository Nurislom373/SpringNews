package org.khasanof.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author Nurislom
 * @see org.khasanof.webflux
 * @since 6/8/2024 11:03 PM
 */
public class StreamFlowAddElementTest {

    @Test
    void firstTestStreamFlowAddElementShouldSuccess() {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> flux = sink.asFlux();

        // Ma'lumotlarni Fluxga qo'shish
        sink.emitNext("Hello", Sinks.EmitFailureHandler.FAIL_FAST);
        sink.emitNext("World", Sinks.EmitFailureHandler.FAIL_FAST);

        flux.subscribe(System.out::println);

        // Oqim boshlangandan keyin ma'lumot qo'shish
        sink.emitNext("More Data", Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
