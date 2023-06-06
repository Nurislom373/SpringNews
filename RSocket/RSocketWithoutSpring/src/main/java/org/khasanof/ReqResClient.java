package org.khasanof;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

/**
 * Author: Nurislom
 * <br/>
 * Date: 04.06.2023
 * <br/>
 * Time: 21:35
 * <br/>
 * Package: org.khasanof
 */
public class ReqResClient {

    private final RSocket rSocket;

    public ReqResClient() {
        this.rSocket = RSocketFactory.connect()
                .transport(TcpClientTransport.create("localhost", ApplicationProperties.TCP_PORT))
                .start().block();
    }

    public String callBlocking(String var) {
        return rSocket.requestResponse(DefaultPayload.create(var))
                .map(Payload::getDataUtf8)
                .onErrorReturn(ApplicationProperties.ERROR_MSG)
                .block();
    }

    public void dispose() {
        this.rSocket.dispose();
    }
}
