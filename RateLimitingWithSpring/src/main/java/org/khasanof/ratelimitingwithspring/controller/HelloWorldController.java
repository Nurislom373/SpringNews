package org.khasanof.ratelimitingwithspring.controller;

import org.khasanof.ratelimitingwithspring.rateLimiting.Limited;
import org.khasanof.ratelimitingwithspring.rateLimiting.Time;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/10/2023
 * <br/>
 * Time: 11:56 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.controller
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value = "/boom/hello", method = RequestMethod.GET)
    @Limited(limit = 60, time = 10, timeType = Time.MINUTE)
    public ResponseEntity<String> boomHello() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/boom/post")
    @Limited(limit = 60, time = 10, timeType = Time.MINUTE)
    public ResponseEntity<String> boomPost() {
        return ResponseEntity.ok().build();
    }

}
