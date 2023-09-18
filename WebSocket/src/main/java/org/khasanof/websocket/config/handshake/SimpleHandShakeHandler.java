package org.khasanof.websocket.config.handshake;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.config.handshake
 * @since 9/18/2023 11:26 PM
 */
@Component
public class SimpleHandShakeHandler extends DefaultHandshakeHandler {

    @Override
    public Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Principal principal = request.getPrincipal();
        if (Objects.isNull(principal)) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
            principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities);
        }
        return principal;
    }

}
