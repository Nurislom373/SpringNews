package org.khasanof.swaggerui2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 9:40 PM
 * <br/>
 * Package: org.khasanof.swaggerui2.controller
 */
@RestController
@RequestMapping(value = "/store/*")
public class StoreController {

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody String message) {
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{message}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable String message) {
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{message}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
