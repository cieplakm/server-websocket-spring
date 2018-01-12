package com.gft.challenge1.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(myHandler(), "/nazwa_jakas_dowlona").withSockJS();
    }

    @Bean
    public WebSocketHandler myHandler(){
        return new MySocketHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketCointiner(){
        ServletServerContainerFactoryBean containerFactoryBean = new ServletServerContainerFactoryBean();
        containerFactoryBean.setMaxBinaryMessageBufferSize(8192);
        containerFactoryBean.setMaxBinaryMessageBufferSize(8192);

        return containerFactoryBean;
    }
}
