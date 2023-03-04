# Integrating Thymeleaf With Spring

First, let's see the configurations required to integrate with Spring. The thymeleaf-spring library is required for the
integration.

```xml

<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
    <version>3.1.1.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring6</artifactId>
    <version>3.1.1.RELEASE</version>
</dependency>
```

Note that, for a Spring 6 project, we have to use the thymeleaf-spring6 library instead of thymeleaf-spring5.

The SpringTemplateEngine class performs all of the configuration steps.

We can configure this class as a bean in the Java configuration file:

```java
@Bean
@Description("Thymeleaf Template Resolver")
public ClassLoaderTemplateResolver templateResolver() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/views/");
    templateResolver.setCacheable(false);
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setCharacterEncoding("UTF-8");
    return templateResolver;
}

@Bean
@Description("Thymeleaf Template Engine")
public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.setTemplateEngineMessageSource(messageSource());
    return templateEngine;
}

@Bean
@Description("Thymeleaf View Resolver")
public ThymeleafViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    return viewResolver;
}
```

XML configuration file:

```xml
<bean id="templateResolver" class="org.thymeleaf.templateresolver.ClassLoaderTemplateResolver">
    <property name="prefix" value="/WEB-INF/views/" />
    <property name="cacheable" value="false" />
    <property name="suffix" value=".html" />
    <property name="templateMode" value="HTML5" />
    <property name="characterEncoding" value="UTF-8" />
</bean>

<bean id="templateEngine" class="org.thymeleaf.spring6.SpringTemplateEngine">
    <property name="templateResolver" ref="templateResolver" />
</bean>

<bean class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
    <property name="templateEngine" ref="templateEngine" />
</bean>
```

The templateResolver bean properties prefix and suffix indicate the location of the view pages within the webapp 
directory and their filename extension, respectively.

The ViewResolver interface in Spring MVC maps the view names returned by a controller to actual view objects. 
ThymeleafViewResolver implements the ViewResolver interface, and it's used to determine which Thymeleaf views to render,
given a view name.

# Integrating Thymeleaf With Spring Boot

Spring Boot provides auto-configuration for Thymeleaf by adding the spring-boot-starter-thymeleaf dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    <version>2.3.3.RELEASE</version>
</dependency>
```

No explicit configuration is necessary. By default, HTML files should be placed in the resources/templates location.