package com.gft.challenge1.server.services;

import com.gft.challenge1.server.websockets.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class PathMonitorService {

    private Subscription subscription;
    private PostOfficeService postOfficeService;

    @Autowired
    public PathMonitorService(Subscription subscription, PostOfficeService postOfficeService) {
        this.subscription = subscription;
        this.postOfficeService = postOfficeService;
    }

    public void monitor(Path path) throws IOException {
//        Nodes.ConvertFunction<String> convertFunction = path1 -> path1.getFileName().toString();
//
//        Observable<WebSocketSubscriber> newlySubscriberObservable = subscription.newlyWebSubscriberObservable();
//        Observable<WebSocketSubscriber> allSubscribersObservable = subscription.subscribers();
//
//        //emit every file in root
//        Observable <Envelope> clientConnectObservable = newlySubscriberObservable
//                .flatMap((Function<WebSocketSubscriber, ObservableSource<Envelope>>) webSocketSubscriber ->
//                        Nodes.path2NodeObservable(path, convertFunction).map(s -> new Envelope<>(webSocketSubscriber, s)));
//
//        //emit single name of changed file in root
//        Observable <Envelope> folderChangedObservable = PathObservables.watch(path)
//                .flatMap( (Function<PathObservables.Event, ObservableSource<Envelope>>) event ->
//                        allSubscribersObservable.map(webSocketSubscriber ->
//                                new Envelope<>(webSocketSubscriber, event.getSubject().getFileName().toString()))
//                );
//
//        clientConnectObservable.subscribe(postOfficeService::sendAsJSON);
//        folderChangedObservable.subscribe(postOfficeService::sendAsJSON);

    }
}
