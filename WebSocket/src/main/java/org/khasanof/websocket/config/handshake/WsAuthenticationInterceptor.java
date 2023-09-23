package org.khasanof.websocket.config.handshake;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.websocket.server.AuthenticationDTO;
import org.khasanof.websocket.service.WsAuthenticationService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.config.handshake
 * @since 9/18/2023 9:48 PM
 */
@Slf4j
@Component
public class WsAuthenticationInterceptor implements ChannelInterceptor {

    private static final String USERNAME_HEADER = "login";
    private static final String PASSWORD_HEADER = "password";

    private final WsAuthenticationService authenticationService;

    public WsAuthenticationInterceptor(WsAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Request pre send : {}", message);
        StompHeaderAccessor headerAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (Objects.equals(StompCommand.CONNECT, headerAccessor.getCommand())) {
            String username = headerAccessor.getFirstNativeHeader(USERNAME_HEADER);
            String password = headerAccessor.getFirstNativeHeader(PASSWORD_HEADER);
            Authentication authentication = authenticationService.getAuthenticatedOrFail(new AuthenticationDTO(username, password));
            headerAccessor.setUser(authentication);
        }
        return message;
    }
}
