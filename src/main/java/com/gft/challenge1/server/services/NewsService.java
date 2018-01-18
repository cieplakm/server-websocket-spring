package com.gft.challenge1.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeRepozitory;
import com.gft.challenge1.server.websockets.Observer;
import io.reactivex.functions.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import sun.nio.cs.ArrayEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class NewsService {
    private static final String EMPTY_JSON = "{}";
    private List<Observer> observers;
    private ObjectMapper objectMapper;
    private NodeRepozitory nodeRepozitory;


    @Autowired
    public NewsService(NodeRepozitory nodeRepozitory) {
        this.nodeRepozitory = nodeRepozitory;
        this.observers = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    private void informObserver(Observer observer, Message messageStream) {
        sendSingleMessage(observer, messageStream);
    }

    private void sendSingleMessage(Observer observer, Message message){
        String json = createJSONString(message);
        try {
            observer.sendJSON(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void informObservers() {
        Stream nodeStream = convertIteratorToStream(nodeRepozitory.getRoot().iterator());
        Stream<Message> messageStream = createMessageStream(nodeStream);
        messageStream.forEach((message -> {
            informObservers(message);
        }));

    }

    private Stream<Message> createMessageStream(Stream<Node> stream){
        return stream.map(new Function<Node, Message>() {
            @Override
            public Message apply(Node node) {
                return new Message("update", node.getPayload());
            }
        });
    }

    private void informObservers(Message messageStream) {
        for (Observer observer : observers){
            informObserver(observer, messageStream);
        }
    }


    public void register(Observer observer){
        observers.add(observer);
    }

    public void unregister(WebSocketSession observerToRemove){
        for (Observer obs : observers){
            if (obs.equals(observerToRemove)){
                observers.remove(obs);
                return;
            }
        }
    }

    private String createJSONString(Message node) {
        String json;
        try {
            json = objectMapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            json = EMPTY_JSON;
        }

        return json;
    }

    private static <T> Stream<T> convertIteratorToStream(Iterator<T> iterator) {
        Iterable<T> iterable = () -> iterator;

        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
