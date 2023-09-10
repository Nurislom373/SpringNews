package org.khasanof.websocket.client.wss;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.netty.ws.NettyWebSocket;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.client.wss
 * @since 9/4/2023 11:41 AM
 */
public abstract class WSSBuilder {

    public static AsyncHttpClient builder() {
        return Dsl.asyncHttpClient();
    }

    public static WSS build(NettyWebSocket webSocket) {
        return new DefaultWSS(webSocket);
    }

}
