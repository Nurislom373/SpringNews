package com.example.completablefuturecrud.controller;

import com.example.completablefuturecrud.entity.user.User;
import com.example.completablefuturecrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUsers(@RequestParam(value = "file") MultipartFile file) {
        service.saveUser(file);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(service.findAll().get(), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.findById(id).join(), HttpStatus.OK);
    }
}
