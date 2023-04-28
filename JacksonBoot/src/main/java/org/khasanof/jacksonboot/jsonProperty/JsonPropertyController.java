package org.khasanof.jacksonboot.jsonProperty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/27/2023
 * <br/>
 * Time: 4:59 PM
 * <br/>
 * Package: org.khasanof.jacksonboot
 */
@RestController
@RequestMapping(value = "/api/property/*")
public class JsonPropertyController {

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ResponseEntity<TestDTO> test(@RequestBody TestDTO dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
