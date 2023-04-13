package org.khasanof.ratelimitingwithspring.test;

import io.github.bucket4j.Bucket;
import org.khasanof.ratelimitingwithspring.core.AdapterLimited;
import org.khasanof.ratelimitingwithspring.core.Limited;
import org.khasanof.ratelimitingwithspring.core.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/*")
public class AreaCalculationController {

    private final AdapterLimited getAllLimited;

    public AreaCalculationController(AdapterLimited getAllLimited) {
        this.getAllLimited = getAllLimited;
    }

    @GetMapping(value = "value")
    @Limited(limit = 60, time = 10, timeType = Time.MINUTE)
    public ResponseEntity<String> value(@RequestHeader(value = "X-api-key") String apiKey) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    @Limited(limit = 30, time = 2, timeType = Time.MINUTE)
    public ResponseEntity<String> check() {
        Map<String, Bucket> allLimitedAPIs = getAllLimited.getAllLimitedAPIs();
        int size = allLimitedAPIs.size();
        System.out.println("size = " + size);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "echo/{value}")
    @Limited(limit = 40, time = 8, timeType = Time.MINUTE)
    public ResponseEntity<String> echo(@PathVariable String value) {
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
