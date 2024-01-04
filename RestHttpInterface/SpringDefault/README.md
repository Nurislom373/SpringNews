# Spring 6 Http Interface

The Spring Framework lets you define an HTTP service as a Java interface with @HttpExchange methods. You can pass such 
an interface to HttpServiceProxyFactory to create a proxy which performs requests through an HTTP client such as 
RestClient or WebClient. You can also implement the interface from an @Controller for server request handling.

```java
@HttpExchange
public interface SpringDefaultRestClient {

    @GetExchange("/get?foo1=bar1&foo2=bar2")
    ResponseEntity<Object> echoGetRequest();

}
```

## Reference

[Spring Doc](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-interface)