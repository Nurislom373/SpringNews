package org.khasanof.reactivewebsocket.config;

import org.khasanof.reactivewebsocket.handler.ReactiveWebSocketHandler;
import org.khasanof.reactivewebsocket.handler.SimpleReactiveWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.reactivewebsocket
 * @since 9/23/2023 11:27 AM
 */
@Configuration
public class ReactiveWSConfig {

    @Autowired
    private SimpleReactiveWebSocketHandler simpleReactiveWebSocketHandler;

    @Autowired
    private ReactiveWebSocketHandler reactiveWebSocketHandler;

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> webSocketHandlerMap = new HashMap<>();
        webSocketHandlerMap.put("/simple", simpleReactiveWebSocketHandler);
        webSocketHandlerMap.put("/reactive", reactiveWebSocketHandler);

        Map<String, CorsConfiguration> configurationMap = new HashMap<>();

        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping(webSocketHandlerMap, 1);
        simpleUrlHandlerMapping.setCorsConfigurations(configurationMap);
        return simpleUrlHandlerMapping;
    }

    private CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(1800L);
        return corsConfiguration;
    }

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter(webSocketService());
    }

    @Bean
    public WebSocketService webSocketService() {
        ReactorNettyRequestUpgradeStrategy strategy = new ReactorNettyRequestUpgradeStrategy();
        return new HandshakeWebSocketService(strategy);
    }
}
