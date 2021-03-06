package com.gft.challenge1.server.websockets;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

public class WebSocketSubscriber {
    private WebSocketSession session;

    public WebSocketSubscriber(@NotNull WebSocketSession session) {
        this.session = session;
    }

    public void sendJSON(String text) throws IOException {
        if (session.isOpen()){
            session.sendMessage(new TextMessage(text));
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WebSocketSubscriber && this.session == ((WebSocketSubscriber) obj).session;
    }

    @Override
    public int hashCode() {
        return session.hashCode();
    }
}
