package org.khasanof.ratelimitingwithspring;

import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingService implements InitializingBean {

    private final AdapterLimited adapterLimited;

    private final ConcurrentHashMap<String, Bucket> concurrentHashMap = new ConcurrentHashMap<>();

    public RateLimitingService(AdapterLimited adapterLimited) {
        this.adapterLimited = adapterLimited;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        concurrentHashMap.putAll(adapterLimited.getAllLimitedAPIs());
        showUrls();
    }

    public Bucket getBucket(String url) {
        return concurrentHashMap.get(url);
    }

    public List<String> getAllUrls() {
        return new ArrayList<>(concurrentHashMap.keySet());
    }

    private void showUrls() {
        concurrentHashMap.entrySet().forEach(System.out::println);
    }


}
