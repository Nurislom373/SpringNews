package org.khasanof.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.RequiredArgsConstructor;
import org.khasanof.hazelcast.factory.HazelcastInstanceFactory;
import org.khasanof.hazelcast.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.hazelcast.service
 * @since 6/10/25
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, InitializingBean {

    private IMap<Long, User> userMap;
    private final HazelcastInstanceFactory hazelcastInstanceFactory;

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void delete(Long id) {
        userMap.delete(id);
    }

    @Override
    public void afterPropertiesSet() {
        HazelcastInstance hazelcastInstance = hazelcastInstanceFactory.create();
        userMap = hazelcastInstance.getMap("userMap");
    }
}
