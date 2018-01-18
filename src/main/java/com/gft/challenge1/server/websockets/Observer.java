package com.gft.challenge1.server.websockets;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

public class Observer {
    private WebSocketSession session;

    Observer(WebSocketSession session) {
        this.session = session;
    }

    public void sendJSON(String text) throws IOException {
        if (session.isOpen()){
            session.sendMessage(new TextMessage(text));
        }

    }

    @Override
    public boolean equals(Object obj) {
        return this.session == obj;
    }
}
