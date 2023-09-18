package org.khasanof.websocket.config;

import org.khasanof.websocket.config.handshake.WsAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.server
 * @since 9/10/2023 10:31 AM
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMBConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private WsAuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private DefaultHandshakeHandler defaultHandshakeHandler;

    @Autowired
    private HandshakeInterceptor handshakeInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("test");
        registry.addEndpoint("test")
                .setHandshakeHandler(defaultHandshakeHandler)
                .withSockJS()
                .setInterceptors(handshakeInterceptor);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(authenticationInterceptor);
    }



}
