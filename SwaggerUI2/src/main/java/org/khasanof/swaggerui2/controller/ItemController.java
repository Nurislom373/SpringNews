package org.khasanof.swaggerui2.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.khasanof.swaggerui2.domain.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLConnection;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/22/2023
 * <br/>
 * Time: 3:42 PM
 * <br/>
 * Package: org.khasanof.swaggerui2.controller
 */
@RestController
@RequestMapping(value = "/item/*")
public class ItemController {

    @Value("classpath:static/swagger-config.json")
    private Resource resource;

    @RequestMapping(value = "get/yaml", method = RequestMethod.GET)
    public ResponseEntity<?> getYamlConfig() throws IOException {
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

//    @RequestMapping(value = "create", method = RequestMethod.POST)
//    public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
//        return new ResponseEntity<>(item, HttpStatus.CREATED);
//    }

//    @RequestMapping(value = "update", method = RequestMethod.PUT)
//    public ResponseEntity<Item> update(@Valid @RequestBody Item item) {
//        return new ResponseEntity<>(item, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
//    public ResponseEntity<String> delete(@PathVariable Long id) {
//        return new ResponseEntity<>("Successfully Deleted - Item", HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "get/{id}", method = RequestMethod.POST)
//    public ResponseEntity<Item> get(@PathVariable Long id) {
//        return new ResponseEntity<>(new Item(id,"Swagger", "Lorem Ipsum", 216.86D), HttpStatus.OK);
//    }
}
