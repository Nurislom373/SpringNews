# Spring Data JPA

Spring Data JPA provides repository support for the Jakarta Persistence API (JPA). It eases development of applications
that need to access JPA data sources.

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
  </dependency>
<dependencies>
```

Must Be Add This Annotation

```java
@EnableJpaRepositories
class Config { … }
```

[Spring Doc](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
[Baeldung Series](https://www.baeldung.com/spring-data)
[Spring Blog](https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl)
