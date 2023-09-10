package org.khasanof.gatlingperformancetest.check_pay.wss;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.springframework.http.HttpHeaders;

import java.util.concurrent.ExecutionException;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest.check_pay.wss
 * @since 9/4/2023 11:49 AM
 */
public abstract class WSSBuilder {

    public static WSS build(String token) throws ExecutionException, InterruptedException {
        NettyWebSocket webSocket = Dsl.asyncHttpClient()
                .prepareGet(getURL())
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token))
                .setRequestTimeout(5000)
                .execute(AbstractWSS.getBuildUpgradeHandler())
                .get();
        return new DefaultWSS(webSocket);
    }

    private static String getURL() {
        return "wss://api.b1nk.uz/ws/" + RandomUtils.nextInt(100, 1000) + "/" +
                RandomStringUtils.randomAlphanumeric(8) + "/websocket";
    }

}
