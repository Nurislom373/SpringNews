package org.khasanof.hazelcast.service;

import org.khasanof.hazelcast.model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.hazelcast.service
 * @since 6/10/25
 */
public interface UserService {

    Optional<User> find(Long id);

    void save(User user);

    void delete(Long id);
}
