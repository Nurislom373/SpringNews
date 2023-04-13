package org.khasanof.ratelimitingwithspring.core;

import io.github.bucket4j.Bucket;
import org.khasanof.ratelimitingwithspring.core.json.JsonConfig;
import org.khasanof.ratelimitingwithspring.core.json.ReadLimit;
import org.khasanof.ratelimitingwithspring.core.json.YamlConfig;
import org.khasanof.ratelimitingwithspring.test.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingService implements CommandLineRunner {

    @Autowired
    private AdapterLimited getAllLimited;

    @Autowired
    private AbstractConfig config;

    @Autowired
    private YamlConfig yamlConfig;

    @Autowired
    private AuthUserRepository repository;

    private final ConcurrentHashMap<String, Map<String, Bucket>> concurrentHashMap = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String userKey, String url) {
        System.out.println("userKey = " + userKey);
        System.out.println("url = " + url);
        Map<String, Bucket> bucketMap = newOrExistBucket(userKey);
        return bucketMap.get(url);
    }

    private Map<String, Bucket> newOrExistBucket(String userKey) {
        if (concurrentHashMap.containsKey(userKey)) {
            return concurrentHashMap.get(userKey);
        } else {
            if (repository.existsByApiKey(userKey)) {
               return concurrentHashMap.computeIfAbsent(userKey, this::getBucket);
            }
            throw new RuntimeException("Invalid Api Key!");
        }
    }

    private Map<String, Bucket> getBucket(String userKey) {
        showUsers();
        return getAllLimited.getAllLimitedAPIs();
    }

    private void showUsers() {
        concurrentHashMap.entrySet().forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Yaml Config");
        yamlConfig.run();

        System.out.println("Started");
        Map<String, Bucket> allLimitedAPIs = getAllLimited.getAllLimitedAPIs();
        System.out.println("allLimitedAPIs.size() = " + allLimitedAPIs.size());
        allLimitedAPIs.entrySet().forEach(System.out::println);
    }
}
