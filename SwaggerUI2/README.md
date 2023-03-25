# 1. Swagger UI

`springdoc-openapi` java library helps to automate the generation of API documentation using spring boot projects.
`springdoc-openapi` works by examining an application at runtime to infer API semantics based on spring configurations,
class structure and various annotations.

Automatically generates documentation in JSON/YAML and HTML format APIs. This documentation can be completed by comments
using swagger-api annotations.

# 2. Getting Started

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.4</version>
</dependency>
```

This will automatically deploy swagger-ui to a spring-boot application

- The Swagger UI page will then be available at `http://server:port/context-path/swagger-ui.html` and the OpenAPI 
description will be available at the following url for json format: `http://server:port/context-path/v3/api-docs`
  - server: The server name or IP
  - port: The server port
  - context-path: The context path of the application

## 2.1 Spring WebMvc support

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-api</artifactId>
    <version>2.0.4</version>
</dependency>
```

## 2.2 Spring WebFlux support

- Documentation can be available in yaml format as well, on the following path : /v3/api-docs.yaml

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
    <version>2.0.4</version>
</dependency>
```

- [Annotation Config](mds/ANNOTATION.md)