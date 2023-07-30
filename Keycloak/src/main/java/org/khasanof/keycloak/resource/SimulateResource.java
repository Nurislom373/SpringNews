package org.khasanof.keycloak.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nurislom
 * @see org.khasanof.keycloak.resource
 * @since 30.07.2023 6:01
 */
@RestController
@RequestMapping(value = "/simulate")
public class SimulateResource {

    @GetMapping
    public ResponseEntity<String> simulate() {
        return new ResponseEntity<>("Hello Users!", HttpStatus.OK);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<String> simulateAdmin() {
        return new ResponseEntity<>("Hello Admins!", HttpStatus.OK);
    }

    @GetMapping(value = "/hi")
    public ResponseEntity<String> hi() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String givenName = (String) jwt.getClaims().get("given_name");
        return new ResponseEntity<>("hi ".concat(givenName), HttpStatus.OK);
    }

}
