package com.gft.challenge1.server.websockets;

import com.gft.challenge1.server.PathObservables;
import com.gft.challenge1.server.services.NewsService;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private NewsService newsService;
    Observable<PathObservables.Event> observ;
    Disposable disposable;

    @Autowired
    public MyWebSocketHandler(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        Observer observer = new Observer(webSocketSession);
//        newsService.register(observer);

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }


    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
       // newsService.unregister(webSocketSession);
        disposable = null;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
