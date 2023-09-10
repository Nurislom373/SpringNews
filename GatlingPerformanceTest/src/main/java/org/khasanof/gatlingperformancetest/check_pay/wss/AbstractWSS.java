package org.khasanof.gatlingperformancetest.check_pay.wss;

import org.asynchttpclient.ws.WebSocketUpgradeHandler;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.client.wss
 * @since 9/3/2023 3:12 PM
 */
public abstract class AbstractWSS implements WSS {

    protected static final MessageConverter messageConverter = new MessageConverter();
    protected static final WSSCompletable completable = new WSSCompletable();
    protected static final WSSListener listener = new WSSListener(completable);

    public static WebSocketUpgradeHandler getBuildUpgradeHandler() {
        WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();
        return builder.addWebSocketListener(listener).build();
    }


}
