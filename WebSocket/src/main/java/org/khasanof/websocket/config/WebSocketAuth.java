package org.khasanof.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.*;

/**
 * @author Nurislom
 * @see org.khasanof.websocket.config
 * @since 9/14/2023 4:04 PM
 */
@Configuration
public class WebSocketAuth extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Autowired
    private WsChannelInterceptor interceptor;

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpTypeMatchers(CONNECT, UNSUBSCRIBE, DISCONNECT).permitAll()
                .simpDestMatchers("/secured/**").authenticated()
                .anyMessage().authenticated();
    }

    @Override
    protected void customizeClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interceptor);
        super.customizeClientInboundChannel(registration);
    }
}
