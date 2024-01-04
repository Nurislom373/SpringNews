package org.khasanof;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/4/2024 10:29 PM
 */
public class DynamicRestRequestTest {

    private static final ObjectMapper objectMapper = new ObjectMapper() {{
        registerModule(new JavaTimeModule());
    }};

    private final HttpClientFactory httpClientFactory = new RestClientFactory();

    @Test
    void testDynamicRestRequest() throws URISyntaxException, JsonProcessingException {
        SpringDefaultRestClient restClient = httpClientFactory.factory();
        ResponseEntity<Object> response = restClient.dynamicRestRequest(new URI("https://postman-echo.com/status/201"), HttpMethod.GET);

        System.out.println("response = " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(response));

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertInstanceOf(Map.class, response.getBody());
        Map<String, Integer> responseBody = (Map<String, Integer>) response.getBody();
        responseBody.containsKey("status");
    }

    @Test
    void testEchoPostRequest() throws JsonProcessingException {
        SpringDefaultRestClient restClient = httpClientFactory.factory();
        ResponseEntity<Object> response = restClient.echoPostRequest(Map.of("foo", "bar1"));

        System.out.println("response = " + objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(response));

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertInstanceOf(Map.class, response.getBody());
    }
}
