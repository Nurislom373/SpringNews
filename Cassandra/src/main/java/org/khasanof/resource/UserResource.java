package org.khasanof.resource;

import lombok.RequiredArgsConstructor;
import org.khasanof.domain.User;
import org.khasanof.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof.resource
 * @since 5/4/26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserResource {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
