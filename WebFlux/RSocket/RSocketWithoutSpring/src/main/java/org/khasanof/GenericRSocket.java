package org.khasanof;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author: Nurislom
 * <br/>
 * Date: 04.06.2023
 * <br/>
 * Time: 21:24
 * <br/>
 * Package: org.khasanof
 */
public interface GenericRSocket<T> {

    Mono<T> requestResponse(T data);

    Mono<Void> fireAndForget(T data);

    Flux<T> requestStream(T data);

    Flux<T> requestChannel(Publisher<T> tPublisher);

}
