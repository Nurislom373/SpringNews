package org.khasanof;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.net.URI;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/4/2024 9:50 PM
 */
@HttpExchange
public interface SpringDefaultRestClient {

    @GetExchange
    ResponseEntity<Object> dynamicRestRequest(URI uri, HttpMethod method);

    @GetExchange("/get?foo1=bar1&foo2=bar2")
    ResponseEntity<Object> echoGetRequest();

    @PostExchange("/post")
    ResponseEntity<Object> echoPostRequest(@RequestBody Map<String, Object> body);

}
