package org.khasanof;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author: Nurislom
 * <br/>
 * Date: 04.06.2023
 * <br/>
 * Time: 21:20
 * <br/>
 * Package: org.khasanof
 */
public class Server {

    private static final Logger LOG = LoggerFactory.getLogger(Server.class);
    private final Disposable server;

    public Server() {
        this.server = RSocketFactory.receive()
                .acceptor((connectionSetupPayload, rSocket) -> Mono.just(new RSocketImpl())
                ).transport(TcpServerTransport.create("localhost", ApplicationProperties.TCP_PORT))
                .start().doOnNext(x -> LOG.info("Server Started"))
                .subscribe();
    }

    public void dispose() {
        this.server.dispose();
    }

    private static class RSocketImpl extends AbstractRSocket {

        @Override
        public Mono<Payload> requestResponse(Payload data) {
            return Mono.just(data)
                    .map(m -> DefaultPayload.create("Hello ".concat(data.getDataUtf8())))
                    .doOnNext(next -> LOG.info("Processing finished!"));
        }

        @Override
        public Mono<Void> fireAndForget(Payload data) {
            return Mono.just(data)
                    .doOnNext(o -> {
                        System.out.println("data = " + o);
                    }).then();
        }

        @Override
        public Flux<Payload> requestStream(Payload data) {
            return Flux.just(data)
                    .doOnNext(o -> {
                        System.out.println("data = " + o);
                    });
        }

        @Override
        public Flux<Payload> requestChannel(Publisher<Payload> payloadPublisher) {
            return Flux.from(payloadPublisher);
        }
    }
}
