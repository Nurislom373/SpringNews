package org.khasanof.hazelcast.factory;

import com.hazelcast.core.HazelcastInstance;

/**
 * @author Nurislom
 * @see org.khasanof.hazelcast.factory
 * @since 6/10/25
 */
public interface HazelcastInstanceFactory {

    HazelcastInstance create();
}
