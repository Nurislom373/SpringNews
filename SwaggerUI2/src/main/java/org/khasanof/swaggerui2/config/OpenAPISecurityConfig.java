package org.khasanof.swaggerui2.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/22/2023
 * <br/>
 * Time: 1:20 PM
 * <br/>
 * Package: org.khasanof.swaggerui2.config
 */
/*
    @SecurityScheme annotation adds the securitySchemes to the components section of the OneAPI Specification.
    @SecurityScheme defines a security mechanism that can be used by our APIs. The supported security schemes are
    APIKey, HTTP Authentication (Basic and Bearer), OAuth2, and OpenID Connect. In this case,
    let's use HTTP Bearer Authentication as our security scheme.

    For HTTP Bearer token-based authentication, we need to choose the security scheme as bearerAuth
    and bearer format as JWT.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring 6 Swagger With Annotation Config",
                version = "${api.version}",
                contact = @Contact(
                        name = "Khasanof", email = "khasanof373@gmail.com", url = "https://github.com/Nurislom373/SpringNews"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://springdoc.org"),
                termsOfService = "http://swagger.io/terms/",
                description = "Spring 6 Swagger Simple Application"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring 6 Wiki Documentation", url = "https://springshop.wiki.github.org/docs"
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Production"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPISecurityConfig {
}
