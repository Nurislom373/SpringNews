package org.khasanof.ratelimitingwithspring.core;

import io.github.bucket4j.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final String HEADER_API_KEY = "X-api-key";
    private static final String HEADER_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
    private static final String HEADER_RETRY_AFTER = "X-Rate-Limit-Retry-After-Seconds";

    @Autowired
    private RateLimitingService rateLimitingService;

    private final Bucket bucket;

    public RateLimitInterceptor() {

        Instant firstRefillTime = ZonedDateTime.now()
                .truncatedTo(ChronoUnit.HOURS)
                .plus(100, ChronoUnit.YEARS)
                .toInstant();

        Bandwidth bandwidth = Bandwidth.classic(5, Refill.intervallyAligned(5,
                Duration.ofSeconds(5), firstRefillTime, false));

        this.bucket = Bucket.builder()
                .addLimit(bandwidth)
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String apiKey = request.getHeader(HEADER_API_KEY);

        if (apiKey == null || apiKey.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: " + HEADER_API_KEY);
            return false;
        }

        String requestRequestURI = request.getRequestURI();
        System.out.println("requestRequestURI = " + requestRequestURI);

//        Bucket tokenBucket = rateLimitingService.resolveBucket(apiKey, requestRequestURI);

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        BlockingBucket blockingBucket = bucket.asBlocking();

        if (probe.isConsumed()) {

            log.info("Aviable Tokens: {}", probe.getRemainingTokens());

            response.addHeader(HEADER_LIMIT_REMAINING, String.valueOf(probe.getRemainingTokens()));
            return true;

        } else {

            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.addHeader(HEADER_RETRY_AFTER, String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "You have exhausted your API Request"); // 429

            return false;
        }
    }


}
