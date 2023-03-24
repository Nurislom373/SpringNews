package org.khasanof.swaggerui2.controller;

import org.khasanof.swaggerui2.domain.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/25/2023
 * <br/>
 * Time: 2:03 AM
 * <br/>
 * Package: org.khasanof.swaggerui2.controller
 */
@RestController
@RequestMapping(value = "/category/*")
public class CategoryController {

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>("Successfully Deleted - Category : " + id, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Category(id, "Swagger", "SWAGGER"), HttpStatus.OK);
    }

}
