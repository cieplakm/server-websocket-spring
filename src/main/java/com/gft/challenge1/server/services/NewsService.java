package com.gft.challenge1.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.NodeFakeRepository;
import com.gft.challenge1.server.OnNodeRepositoryChangedEvent;
import com.gft.challenge1.server.websockets.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
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
    public NewsService(NodeFakeRepository fakeRepository) {
        this.nodeRepository = fakeRepository;
        this.observers = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    private void sendClearMessage(){
        sendWholeDataToAllObservers(new Message("clear",null));
    }

    private void informObserver(Observer observer, Message messageStream) {
        sendMessage2Observer(observer, messageStream);
    }

    public Stream<Message> prepareData(){
        Iterator<Node<String>> nodeIterator = nodeRepository.getRoot().iterator();
        Stream<Node<String>> nodeStream = convertIteratorToStream(nodeIterator);
        Stream<Message> messageStream = createMessageStream(nodeStream);

        return messageStream;
    }

    public void sendWholeDataToAllObservers() {
        sendClearMessage();
        prepareData().forEach((this::sendWholeDataToAllObservers));
    }

    private void sendDataToOneObserver(Observer observer){
        prepareData().forEach((message)->{
            sendMessage2Observer(observer, message);
        });
    }

    private Stream<Message> createMessageStream(Stream<Node<String>> stream){
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
        sendDataToOneObserver(observer);
    }

    public void unregister(WebSocketSession observerToRemove){
        for (Observer obs : observers){
            if (obs.equals(observerToRemove)){
                observers.remove(obs);
                return;
            }
        }
    }

    @EventListener
    public void onApplicationEvent(OnNodeRepositoryChangedEvent onNodeRepositoryChangedEvent) {
        sendWholeDataToAllObservers();
    }



}
