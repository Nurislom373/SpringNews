package org.khasanof.ratelimitingwithspring.rateLimiting;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class PricingPlanService {

    private final ConcurrentHashMap<String, Bucket> map = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return map.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String apiKey) {
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey);
        return bucket(pricingPlan.getLimit());
    }

    private Bucket bucket(Bandwidth limit) {
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

}
