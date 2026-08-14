package org.khasanof.riak.config;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "riak")
public class RiakConfig {

    private int port;
    private String host;

    @Bean
    public RiakClient riakClient() {
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress(host)
                .withRemotePort(port)
                .build();

        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        cluster.start();
        return new RiakClient(cluster);
    }
}
