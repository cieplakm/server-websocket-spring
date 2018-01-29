package com.gft.challenge1.server.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private Subscription subscription;

    @Autowired
    public MyWebSocketHandler(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        subscription.subscribe(new WebSocketSubscriber(webSocketSession));
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
       subscription.unsubscribe(new WebSocketSubscriber(webSocketSession));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
