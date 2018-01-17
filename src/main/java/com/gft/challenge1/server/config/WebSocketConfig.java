package com.gft.challenge1.server.config;

import com.gft.challenge1.server.websockets.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    MyWebSocketHandler customHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(customHandler, "/name_of_handler");
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketCointiner(){
        ServletServerContainerFactoryBean containerFactoryBean = new ServletServerContainerFactoryBean();
        containerFactoryBean.setMaxBinaryMessageBufferSize(8192);
        containerFactoryBean.setMaxBinaryMessageBufferSize(8192);

        return containerFactoryBean;
    }

}
