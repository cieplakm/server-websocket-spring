package com.gft.challenge1.server.services;

import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import lombok.Data;
import lombok.Value;


@Value
public class Envelope {
    WebSocketSubscriber webSocketSubscriber;
    String data;
}
