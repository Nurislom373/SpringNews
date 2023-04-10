package org.khasanof.ratelimitingwithspring.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.khasanof.ratelimitingwithspring.rateLimiting.AdapterLimited;
import org.khasanof.ratelimitingwithspring.rateLimiting.Limited;
import org.khasanof.ratelimitingwithspring.rateLimiting.Time;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/*")
public class AreaCalculationController {

    private final Bucket bucket;

    private final AdapterLimited getAllLimited;

    public AreaCalculationController(AdapterLimited getAllLimited) {
        this.getAllLimited = getAllLimited;
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(2)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping(value = "value")
    @Limited(limit = 60, time = 10, timeType = Time.MINUTE)
    public ResponseEntity<String> value() {
        if (bucket.tryConsume(1)) {
            bucket.getAvailableTokens();
            return new ResponseEntity<>(String.valueOf(bucket.getAvailableTokens()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
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
