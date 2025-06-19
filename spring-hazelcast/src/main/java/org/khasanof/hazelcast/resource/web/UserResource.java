package org.khasanof.hazelcast.resource.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.hazelcast.model.User;
import org.khasanof.hazelcast.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.hazelcast.resource.web
 * @since 6/10/25
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> find(@PathVariable Long userId) {
        log.info("Find user by id: {}", userId);
        Optional<User> userOptional = userService.find(userId);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody User user) {
        log.info("Saving user: {}", user);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        log.info("Deleting userId: {}", userId);
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
