package org.khasanof.ratelimitingwithspring.rateLimiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PricingPlanService implements CommandLineRunner {

    @Autowired
    private AdapterLimited getAllLimited;

    private final ConcurrentHashMap<String, Bucket> map = new ConcurrentHashMap<>();

    private final Map<String, Bucket> buckets = new HashMap<>();

    private final ConcurrentHashMap<String, Map<String, Bucket>> concurrentHashMap = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return map.computeIfAbsent(apiKey, this::newBucket);
    }

    public Bucket resolveBucket(String userKey, String url) {
        Map<String, Bucket> bucketMap = newOrExistBucket(userKey);
        return bucketMap.get(url);
    }

    private Bucket newBucket(String apiKey) {
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey);
        return bucket(pricingPlan.getLimit());
    }

    private Map<String, Bucket> newOrExistBucket(String userKey) {
        return concurrentHashMap.computeIfAbsent(userKey, this::getBucket);
    }

    private Map<String, Bucket> getBucket(String userKey) {
        return buckets;
    }

    private Bucket bucket(Bandwidth limit) {
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started");
        Map<String, Bucket> allLimitedAPIs = getAllLimited.getAllLimitedAPIs();
        buckets.putAll(allLimitedAPIs);
        System.out.println("allLimitedAPIs.size() = " + allLimitedAPIs.size());
        allLimitedAPIs.entrySet().forEach(System.out::println);
    }
}
