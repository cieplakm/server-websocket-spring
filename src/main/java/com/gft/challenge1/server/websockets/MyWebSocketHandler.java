package com.gft.challenge1.server.websockets;

import com.gft.challenge1.server.PathObservable;
import com.gft.challenge1.server.services.NewsService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private NewsService newsService;

    @Autowired
    public MyWebSocketHandler(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        Observer observer = new Observer(webSocketSession);
        newsService.register(observer);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }


    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        newsService.unregister(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
