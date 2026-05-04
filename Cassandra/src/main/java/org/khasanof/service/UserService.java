package org.khasanof.service;

import lombok.RequiredArgsConstructor;
import org.khasanof.domain.User;
import org.khasanof.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 5/4/26
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User create(String name, Integer age) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setAge(age);
        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }
}
