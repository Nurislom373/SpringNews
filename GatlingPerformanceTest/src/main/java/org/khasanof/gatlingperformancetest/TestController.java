package org.khasanof.gatlingperformancetest;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Nurislom
 * @see org.khasanof.gatlingperformancetest
 * @since 8/24/2023 5:57 PM
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class TestController {

    private final Faker faker;

    public TestController(Faker faker) {
        this.faker = faker;
    }

    @GetMapping(value = "/get-fake-data")
    public ResponseEntity<Object> testOne() {
        log.info("test one call!");
        return ResponseEntity.ok(new HashMap<>(){{
            put("firstName", faker.name().firstName());
            put("lastName", faker.name().lastName());
            put("company", faker.company().name());
        }});
    }

    @PostMapping(value = "/create-fake-data")
    public ResponseEntity<Object> testCreate(TestDTO testDTO) {
        log.info("test dto : {}", testDTO);
        return new ResponseEntity<>(testDTO, HttpStatus.CREATED);
    }

}
