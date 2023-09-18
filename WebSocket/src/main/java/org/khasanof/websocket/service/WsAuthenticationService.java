package org.khasanof.websocket.service;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.websocket.server.AuthenticationDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.service
 * @since 9/18/2023 9:52 PM
 */
@Slf4j
@Service
public class WsAuthenticationService {

    private final String ANONYMOUS = "ANONYMOUS_ROLE";

    public Authentication getAuthenticatedOrFail(AuthenticationDTO authentication) {
        return new UsernamePasswordAuthenticationToken(authentication.getUsername(), authentication.getPassword(),
                Collections.singleton((GrantedAuthority) () -> ANONYMOUS));
    }

}
