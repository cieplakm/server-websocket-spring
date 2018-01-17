package com.gft.challenge1.server.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServerObservers {
    List<WebSocketSession> observers;

    public ServerObservers() {
        observers = new ArrayList<>();
    }

    public void register(WebSocketSession handler){
        observers.add(handler);
    }

    public void unregister(WebSocketSession handler){
        observers.remove(handler);
    }

    public void sendToObservers(String json){
        for (WebSocketSession handler : observers){
            try {
                handler.sendMessage(new TextMessage(json));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
