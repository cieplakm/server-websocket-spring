package com.gft.challenge1.server.websockets;

import com.gft.challenge1.server.ServerObservers;
import com.gft.challenge1.server.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;


@Component
public class MyWebSocketHandler implements WebSocketHandler {

    @Autowired
    FilesService filesService;

    private ServerObservers serverObservers;


    @Autowired
    public MyWebSocketHandler(ServerObservers serverObservers) {
        this.serverObservers = serverObservers;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        serverObservers.register(new Client(webSocketSession));
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }


    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        serverObservers.unregister(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
