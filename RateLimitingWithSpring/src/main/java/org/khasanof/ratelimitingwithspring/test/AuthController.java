package org.khasanof.ratelimitingwithspring.test;

import lombok.RequiredArgsConstructor;
import org.khasanof.ratelimitingwithspring.domain.AuthUserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/11/2023
 * <br/>
 * Time: 1:50 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.test
 */
@RestController
@RequestMapping(value = "/auth/*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUserService service;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody AuthUserEntity user) {
        service.create(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO dto) {
        return new ResponseEntity<>(service.login(dto), HttpStatus.OK);
    }
}
