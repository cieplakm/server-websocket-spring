package com.gft.challenge1.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class PostOfficeService {
    private static final String EMPTY_JSON = "{}";
    private ObjectMapper objectMapper;

    @Autowired
    public PostOfficeService() {
        this.objectMapper = new ObjectMapper();
    }

    public void sendAsJSON(Envelope envelope){
        Message message = new Message("update", envelope.getData());

        try {
            envelope.getWebSocketSubscriber().sendJSON(message2JSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String message2JSONString(Message node) {
        String json;
        try {
            json = objectMapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            json = EMPTY_JSON;
        }

        return json;
    }

}
