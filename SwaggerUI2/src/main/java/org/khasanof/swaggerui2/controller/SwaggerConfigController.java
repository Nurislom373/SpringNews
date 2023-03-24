package org.khasanof.swaggerui2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/24/2023
 * <br/>
 * Time: 6:34 PM
 * <br/>
 * Package: org.khasanof.swaggerui2.controller
 */
@RestController
@RequestMapping(value = "/config/")
public class SwaggerConfigController {

    // Get Swagger JSON Config
    @Value("classpath:static/swagger-config.json")
    private Resource resourceJson;

    // Get Swagger YAML Config
    @Value("classpath:static/swagger-config.yaml")
    private Resource resourceYaml;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public ResponseEntity<Resource> jsonConfigGet() {
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resourceJson.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resourceJson);
    }

    @RequestMapping(value = "yaml", method = RequestMethod.GET)
    public ResponseEntity<Resource> yamlConfigGet() {
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resourceYaml.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resourceYaml);
    }



}
