package org.khasanof.riak.controller;

import lombok.RequiredArgsConstructor;
import org.khasanof.riak.model.User;
import org.khasanof.riak.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.delete(id);
    }
}