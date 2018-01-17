package com.gft.challenge1.server.websockets;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

public class Client {
    private WebSocketSession session;

    Client(WebSocketSession session) {
        this.session = session;
    }

    public void sendTest(String text) throws IOException {
        session.sendMessage(new TextMessage(text));
    }

    @Override
    public boolean equals(Object obj) {
        return this.session == obj;
    }
}
