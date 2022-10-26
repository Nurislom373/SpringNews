package org.khasanof.servicetwo;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTwoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri("http://localhost:9090")
                .build();
    }
}


@RestController
@RequestMapping(value = "/api/")
class ServiceController {

    @Autowired
    private RestTemplate restTemplate;


    /*
        The circuit breaker pattern protects a downstream service by
        restricting the upstream service from calling the downstream service
        during a partial or complete downtime.
    */
    @RequestMapping(value = "circuit-breaker", method = RequestMethod.GET)
    @CircuitBreaker(name = "CircuitBreakerService")
    public ResponseEntity<String> sayCircuitBreaker() {
        return new ResponseEntity<>(restTemplate.getForObject("/api/say", String.class), HttpStatus.OK);
    }


    /*
        The retry pattern provides resiliency to a system by
        recovering from transient issues.
     */
    @RequestMapping(value = "retry", method = RequestMethod.GET)
    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public ResponseEntity<String> sayRetry() {
        return new ResponseEntity<>(restTemplate.getForObject("/api/say", String.class), HttpStatus.OK);
    }

    public ResponseEntity<String> fallbackAfterRetry(Exception ex) {
        return new ResponseEntity<>("Service is Down! with retry", HttpStatus.SERVICE_UNAVAILABLE);
    }

}

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler({CallNotPermittedException.class})
    public ResponseEntity<String> handleCallNotPermittedException() {
        return new ResponseEntity<>("Service is Down! with circuit-breaker", HttpStatus.SERVICE_UNAVAILABLE);
    }

}

