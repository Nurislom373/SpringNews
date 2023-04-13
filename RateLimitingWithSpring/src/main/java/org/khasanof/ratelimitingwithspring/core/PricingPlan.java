package org.khasanof.ratelimitingwithspring.core;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public enum PricingPlan {
    FREE(10, Duration.ofMinutes(2)),
    BASIC(50, Duration.ofMinutes(2)),
    PRO(100, Duration.ofMinutes(2)),
    SUPER(1000, Duration.ofMinutes(2));
    private final int capacity;
    private final Duration duration;

    private PricingPlan(int capacity, Duration duration) {
        this.capacity = capacity;
        this.duration = duration;
    }

    Bandwidth getLimit() {
        return Bandwidth.classic(capacity, Refill.intervally(capacity, duration));
    }

    public int bucketCapacity() {
        return capacity;
    }

    static PricingPlan resolvePlanFromApiKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return FREE;
        } else if (apiKey.startsWith("PX001-")) {
            return PRO;
        } else if (apiKey.startsWith("BX001-")) {
            return BASIC;
        } else if (apiKey.startsWith("SX001-")) {
            return SUPER;
        }
        return FREE;
    }
}
