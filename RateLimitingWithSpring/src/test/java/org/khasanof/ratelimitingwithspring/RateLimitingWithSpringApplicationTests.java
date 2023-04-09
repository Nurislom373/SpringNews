package org.khasanof.ratelimitingwithspring;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RateLimitingWithSpringApplicationTests {

    @Test
    void contextLoads() {
        Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(10, refill);
        Bucket bucket = Bucket.builder()
                .addLimit(limit)
                .build();

        for (int i = 1; i <= 10; i++) {
            Assertions.assertTrue(bucket.tryConsume(1));
        }
        Assertions.assertFalse(bucket.tryConsume(1));
    }

    @Test
    void otherTestRateLimit() {
        Bandwidth limit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(2)));
        Bucket bucket = Bucket.builder()
                .addLimit(limit)
                .build();
        Assertions.assertTrue(bucket.tryConsume(1));     // first request
        Executors.newScheduledThreadPool(1)   // schedule another request for 2 seconds later
                .schedule(() -> Assertions.assertTrue(bucket.tryConsume(1)), 2, TimeUnit.SECONDS);
    }

}
