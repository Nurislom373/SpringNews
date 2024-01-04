package org.khasanof;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringDefaultApplicationTests {

    @Test
    void testRestClientFactory() {
        HttpClientFactory httpClientFactory = new RestClientFactory();
        SpringDefaultRestClient restClient = httpClientFactory.factory();
        ResponseEntity<Object> response = restClient.echoGetRequest();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertInstanceOf(Map.class, response.getBody());
    }

    @Test
    void testRestTemplateFactory() {
        HttpClientFactory httpClientFactory = new RestTemplateFactory();
        SpringDefaultRestClient restClient = httpClientFactory.factory();
        ResponseEntity<Object> response = restClient.echoGetRequest();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertInstanceOf(Map.class, response.getBody());
    }

    @Test
    void testWebClientFactory() {
        HttpClientFactory httpClientFactory = new WebClientFactory();
        SpringDefaultRestClient restClient = httpClientFactory.factory();
        ResponseEntity<Object> response = restClient.echoGetRequest();
        System.out.println("response = " + response);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertInstanceOf(Map.class, response.getBody());
    }
}
