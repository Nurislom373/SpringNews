package org.khasanof.ratelimitingwithspring.rateLimiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping(value = "api/v1/*")
public class AreaCalculationController {

    private final Bucket bucket;

    public AreaCalculationController() {
        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(2)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping(value = "get")
    public ResponseEntity<String> value() {
        if (bucket.tryConsume(1)) {
            bucket.getAvailableTokens();
            return new ResponseEntity<>(String.valueOf(bucket.getAvailableTokens()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping(value = "echo/{value}")
    public ResponseEntity<String> echo(@PathVariable String value) {
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
