package org.khasanof.factory;

import com.clickhouse.client.api.Client;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 6/9/25
 */
public class ClickhouseClientFactoryImpl implements ClickhouseClientFactory {

    @Override
    public Client createClient() {
        return new Client.Builder()
                .addEndpoint("http://localhost:8123/")
                .setUsername("dbuser")
                .setPassword("password")
                .setDefaultDatabase("clickhousedb")
                .build();
    }
}
