package com.gft.challenge1.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
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
    private List<WebSocketSubscriber> webSocketSubscribers;
    private ObjectMapper objectMapper;


    @Autowired
    public NewsService() {

        this.webSocketSubscribers = new ArrayList<>();
        this.objectMapper = new ObjectMapper();




    }

    private void sendClearMessage(){
        sendWholeDataToAllObservers(new Message("clear",null));
    }

    private void informObserver(WebSocketSubscriber webSocketSubscriber, Message messageStream) {
        sendMessage2Observer(webSocketSubscriber, messageStream);
    }

    public Stream<Message> prepareData(){
        Iterator<Node<String>> nodeIterator = null; //= nodeRepository.getRoot().iterator();
        Stream<Node<String>> nodeStream = convertIteratorToStream(nodeIterator);
        Stream<Message> messageStream = createMessageStream(nodeStream);

        return messageStream;
    }

    public void sendWholeDataToAllObservers() {
        sendClearMessage();
        prepareData().forEach((this::sendWholeDataToAllObservers));
    }

    private void sendDataToOneObserver(WebSocketSubscriber webSocketSubscriber){
        prepareData().forEach((message)->{
            sendMessage2Observer(webSocketSubscriber, message);
        });
    }

    private Stream<Message> createMessageStream(Stream<Node<String>> stream){
        return stream.map(node -> new Message<>("update", node.getPayload()));
    }

    private void sendWholeDataToAllObservers(Message messageStream) {
        for (WebSocketSubscriber webSocketSubscriber : webSocketSubscribers){
            informObserver(webSocketSubscriber, messageStream);
        }
    }

    private void sendMessage2Observer(WebSocketSubscriber webSocketSubscriber, Message message){
        String json = message2JSONString(message);
        try {
            webSocketSubscriber.sendJSON(json);
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

    public void register(WebSocketSubscriber webSocketSubscriber){
        webSocketSubscribers.add(webSocketSubscriber);
        sendDataToOneObserver(webSocketSubscriber);
    }

    public void unregister(WebSocketSession observerToRemove){
        for (WebSocketSubscriber obs : webSocketSubscribers){
            if (obs.equals(observerToRemove)){
                webSocketSubscribers.remove(obs);
                return;
            }
        }
    }

}
