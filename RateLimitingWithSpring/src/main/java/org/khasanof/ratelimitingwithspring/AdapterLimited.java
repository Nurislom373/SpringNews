package org.khasanof.ratelimitingwithspring;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucket;
import org.khasanof.ratelimitingwithspring.annotation.Limited;
import org.khasanof.ratelimitingwithspring.enums.RefillType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/9/2023
 * <br/>
 * Time: 11:12 PM
 * <br/>
 * Package: org.khasanof.ratelimitingwithspring.rateLimiting
 */
@Component
public class AdapterLimited {

    private final ApplicationContext context;

    private final List<Class<? extends Annotation>> annotations = List.of(
            RequestMapping.class, GetMapping.class, PostMapping.class,
            DeleteMapping.class, PutMapping.class, PatchMapping.class
    );

    public AdapterLimited(ApplicationContext context) {
        this.context = context;
    }

    public Map<String, Bucket> getAllLimitedAPIs() {
        Map<String, Bucket> bucketMap = new HashMap<>();

        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beansWithAnnotation.values();
        System.out.println("values = " + values);

        Predicate<Object> requestMappingPredicate = (o) -> classLevelPresentAnnotation(o, RequestMapping.class);
        Predicate<Object> limitedMethod = (o) -> objectMethodsPresentAnnotation(o, Limited.class);
        Predicate<Object> requestMappingMethod = (o) -> objectMethodsPresentAnnotation(o, RequestMapping.class);

        Map<Boolean, List<Object>> listMap = values.stream()
                .collect(Collectors.partitioningBy(requestMappingPredicate));

        System.out.println("listMap.size() = " + listMap.size());

        listMap.entrySet().stream().flatMap(e -> e.getValue().stream()
                .filter(limitedMethod.and(requestMappingMethod))
                .flatMap(c -> Arrays.stream(c.getClass().getMethods())
                        .filter(method -> methodLevelPresentAnnotation(method, annotations))
                        .filter(method -> methodLevelPresentAnnotation(method, Limited.class))
                        .map(m -> {
                            if (e.getKey()) {
                                return new AbstractMap.SimpleEntry<>(getMethodUrl(m, getClassUrl(c)), getBucketMethod(m));
                            } else {
                                return new AbstractMap.SimpleEntry<>(getMethodUrl(m, null), getBucketMethod(m));
                            }
                        })
                )).forEach(map -> bucketMap.put(map.getKey(), map.getValue()));
        return bucketMap;
    }

    private boolean objectMethodsPresentAnnotation(Object object, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(object.getClass().getMethods())
                .anyMatch(m -> methodLevelPresentAnnotation(m, annotationClass));
    }

    private boolean methodLevelPresentAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    private boolean methodLevelPresentAnnotation(Method method, List<Class<? extends Annotation>> annotations) {
        return annotations.stream().anyMatch(a -> methodLevelPresentAnnotation(method, a));
    }

    private boolean classLevelPresentAnnotation(Object o, Class<? extends Annotation> annotationClass) {
        return o.getClass().isAnnotationPresent(annotationClass);
    }

    private Bucket getBucketMethod(Method method) {
        Limited limited = method.getAnnotation(Limited.class);
        return getLocalBucket(limited);
    }

    private LocalBucket getLocalBucket(Limited limited) {
        Bandwidth bandwidth;
        if (limited.refillType().equals(RefillType.GREEDY)) {
            bandwidth = Bandwidth.classic(limited.limit(),
                    Refill.greedy(limited.limit(), getDuration(limited)));
        } else {
            bandwidth = Bandwidth.classic(limited.limit(),
                    Refill.intervally(limited.limit(), getDuration(limited)));
        }
        return Bucket.builder()
                .addLimit(bandwidth)
                .build();
    }

    private String getMethodUrl(Method method, String classUrl) {
        String s = getRequestUrl(method);

        if (classUrl != null && !classUrl.isBlank()) {
            return replaceString(classUrl, s);
        }

        return s;
    }

    private String getRequestUrl(Method method) {
        if (method.isAnnotationPresent(RequestMapping.class)) {
            return method.getAnnotation(RequestMapping.class).value()[0];
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            return method.getAnnotation(GetMapping.class).value()[0];
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            return method.getAnnotation(PostMapping.class).value()[0];
        } else if (method.isAnnotationPresent(PutMapping.class)) {
            return method.getAnnotation(PutMapping.class).value()[0];
        } else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return method.getAnnotation(DeleteMapping.class).value()[0];
        } else {
            return method.getAnnotation(PatchMapping.class).value()[0];
        }
    }

    private String replaceString(String var, String url) {
        String concat = var.replace('*', ' ')
                .strip().concat(url);
        System.out.println("concat = " + concat);
        return concat;
    }

    private String getClassUrl(Object object) {
        String url = object.getClass().getAnnotation(RequestMapping.class).value()[0];
        System.out.println("url = " + url);
        return url;
    }

    private Duration getDuration(Limited limited) {
        return switch (limited.timeType()) {
            case SECOND -> Duration.ofSeconds(limited.time());
            case MINUTE -> Duration.ofMinutes(limited.time());
            case HOUR -> Duration.ofHours(limited.time());
            case DAY -> Duration.ofDays(limited.time());
        };
    }

}
