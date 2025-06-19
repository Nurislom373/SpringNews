package org.khasanof.hazelcast.factory;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.hazelcast.factory
 * @since 6/10/25
 */
@Component
public class HazelcastInstanceFactoryImpl implements HazelcastInstanceFactory {

    @Override
    public HazelcastInstance create() {
        return Hazelcast.newHazelcastInstance(getHazelcastConfig());
    }

    private Config getHazelcastConfig() {
        Config config = new Config();
        config.setClusterName("hazelcast-cluster");
        return config;
    }
}
