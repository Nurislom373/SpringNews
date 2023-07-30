package org.khasanof.keycloak.config.logout;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Nurislom
 * @see org.khasanof.keycloak.config.logout
 * @since 30.07.2023 7:25
 */
/*
    keycloak logout handler use mvc projects!
 */
@Component
@RequiredArgsConstructor
public class KeycloakLogoutHandler implements LogoutHandler {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakLogoutHandler.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutFromKeycloak((OidcUser) authentication.getPrincipal());
    }

    private void logoutFromKeycloak(OidcUser oidcUser) {
        String sessionEndpoint = oidcUser.getIssuer() + "/protocol/openid-connect/logout";
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromUriString(sessionEndpoint)
                .queryParam("id_token_hint", oidcUser.getIdToken().getTokenValue());
        ResponseEntity<String> response = restTemplate.getForEntity(componentsBuilder.toUriString(), String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Successfully logged out from Keycloak");
        } else {
            logger.error("Could not propagate logout to Keycloak");
        }
    }
}
