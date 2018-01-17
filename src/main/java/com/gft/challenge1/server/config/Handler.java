package com.gft.challenge1.server.config;

import com.gft.challenge1.server.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;

@Component
public class Handler implements WebSocketHandler {

    @Autowired
    FilesService filesService;

    private ServerObservers serverObservers;
    private WebSocketSession webSocketSession;

    @Autowired
    public Handler(ServerObservers serverObservers) {
        this.serverObservers = serverObservers;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        this.webSocketSession = webSocketSession;
        serverObservers.register(webSocketSession);
        filesService.send();
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

    public void sendDataToClient(String json){
        try {
            webSocketSession.sendMessage(new TextMessage(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
