package com.gft.challenge1.server.services;

import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import lombok.Value;

@Value
public class Envelope {
    private WebSocketSubscriber webSocketSubscriber;
    private String data;
}
