package com.gft.challenge1.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeFakeRepository;
import com.gft.challenge1.server.websockets.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class NewsService {
    private static final String EMPTY_JSON = "{}";
    private List<Observer> observers;
    private ObjectMapper objectMapper;
    private NodeFakeRepository nodeRepository;

    @Autowired
    public NewsService(NodeFakeRepository nodeRepozitory) {
        this.nodeRepository = nodeRepozitory;
        this.observers = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    private void sendClearMessage(){
        sendWholeDataToAllObservers(new Message("clear",null));
    }

    private void informObserver(Observer observer, Message messageStream) {
        sendMessage2Observer(observer, messageStream);
    }

    public void sendWholeDataToAllObservers() {
        sendClearMessage();

        Iterator<Node> nodeIterator = nodeRepository.getRoot().iterator();
        Stream<Node> nodeStream = convertIteratorToStream(nodeIterator);
        Stream<Message> messageStream = createMessageStream(nodeStream);

        messageStream.forEach((this::sendWholeDataToAllObservers));
    }

    private Stream<Message> createMessageStream(Stream<Node> stream){
        return stream.map(node -> new Message<>("update", node.getPayload()));
    }

    private void sendWholeDataToAllObservers(Message messageStream) {
        for (Observer observer : observers){
            informObserver(observer, messageStream);
        }
    }

    private void sendMessage2Observer(Observer observer, Message message){
        String json = message2JSONString(message);
        try {
            observer.sendJSON(json);
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

    private static <T> Stream<T> convertIteratorToStream(Iterator<T> iterator) {
        Iterable<T> iterable = () -> iterator;

        return StreamSupport.stream(iterable.spliterator(), false);
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
}
