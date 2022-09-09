package org.khasanof.authentication.controller;

import org.khasanof.authentication.dto.UserCreateDTO;
import org.khasanof.authentication.dto.UserRequestDTO;
import org.khasanof.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/auth/*")
public class AuthController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody UserRequestDTO dto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserRequestDTO> entity = new HttpEntity<>(dto);
        ResponseEntity<Object> exchange = restTemplate.exchange("http://localhost:8081/api/login", HttpMethod.POST, entity, Object.class);
        return new ResponseEntity<>(exchange.getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserCreateDTO dto) {
        service.register(dto);
        return new ResponseEntity<>("Success Register", HttpStatus.CREATED);
    }
}
