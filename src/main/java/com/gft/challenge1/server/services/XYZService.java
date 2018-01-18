package com.gft.challenge1.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.challenge1.server.ServerObservers;
import com.gft.challenge1.server.node.Node;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class XYZService {
    private static final String EMPTY_JSON = "{}";
    private ServerObservers observers;
    private ObjectMapper objectMapper;

    public XYZService(ServerObservers observers) {
        this.observers = observers;
        this.objectMapper = new ObjectMapper();
    }

    public void send(){
            Iterator<Node> iterator = FakeRootNode.getIter();

            Stream<Node>stream = convertIteratorToStream(iterator);

            stream.forEach(this::emitToObserver);

    }

    private void emitToObserver(Node node) {
        String json;
        try {
            json = objectMapper.writeValueAsString(node.getPayload());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            json = EMPTY_JSON;
        }

        observers.informObservers(json);
    }

    public static <T> Stream<T> convertIteratorToStream(Iterator<T> iterator) {
        Iterable<T> iterable = () -> iterator;

        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
