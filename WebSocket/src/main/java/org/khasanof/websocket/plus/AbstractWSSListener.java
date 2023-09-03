package org.khasanof.websocket.plus;

import org.asynchttpclient.ws.WebSocketUpgradeHandler;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.plus
 * @since 9/3/2023 3:12 PM
 */
public abstract class AbstractWSSListener implements WSS {

    protected static final MessageConverter messageConverter = new MessageConverter();
    protected static final WSSCompletable completable = new WSSCompletable();
    protected static final WSSListener listener = new WSSListener(completable);

    public static WebSocketUpgradeHandler getBuildUpgradeHandler() {
        WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();
        return builder.addWebSocketListener(listener).build();
    }


}
