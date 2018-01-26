package com.gft.challenge1.server.websockets;

import com.gft.challenge1.server.services.Subscription;
import io.reactivex.functions.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private Subscription subscription;

    @Autowired
    public MyWebSocketHandler(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        subscription.newlyWebSubscriberObservable().subscribe(new Consumer<WebSocketSubscriber>() {
            @Override
            public void accept(WebSocketSubscriber webSocketSubscriber) throws Exception {
                System.out.println("przed!");
            }
        });

        subscription.subscribe(new WebSocketSubscriber(webSocketSession));


    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }


    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        subscription.newlyWebSubscriberObservable().replay().autoConnect().subscribe(new Consumer<WebSocketSubscriber>() {
            @Override
            public void accept(WebSocketSubscriber webSocketSubscriber) throws Exception {
                System.out.println("OOOOOBY");
            }
        });

       subscription.unsubscribe(new WebSocketSubscriber(webSocketSession));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
