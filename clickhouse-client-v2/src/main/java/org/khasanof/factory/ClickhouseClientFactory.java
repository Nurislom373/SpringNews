package org.khasanof.factory;

import com.clickhouse.client.api.Client;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 6/9/25
 */
public interface ClickhouseClientFactory {

    Client createClient();
}
