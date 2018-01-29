package com.gft.challenge1.server.services;

import com.gft.challenge1.server.websockets.Subscription;
import com.gft.challenge1.server.node.Nodes;
import com.gft.challenge1.server.path.PathObservables;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class NodePathObserver {

    private Subscription subscription;
    private PostOfficeService postOfficeService;

    @Autowired
    public NodePathObserver(Path myPath, Subscription subscription, PostOfficeService postOfficeService) {
        this.subscription = subscription;
        this.postOfficeService = postOfficeService;

        try {
            createNodeFromPath(myPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNodeFromPath(Path path) throws IOException {

        Nodes.ConvertFunction<String> convertFunction = Path::toString;


        Observable<WebSocketSubscriber> newlySubscriberObservable = subscription.newlyWebSubscriberObservable();
        Observable<WebSocketSubscriber> allSubscribersObservable = subscription.subscribers();


        Observable <Envelope> clientConnectObservable = newlySubscriberObservable
                .flatMap((Function<WebSocketSubscriber, ObservableSource<Envelope>>) webSocketSubscriber ->
                        Nodes.path2NodeObservable(path, convertFunction).map(s -> new Envelope(webSocketSubscriber, s)));



        Observable <Envelope> folderCangedObservable = PathObservables.watch(path)
                .flatMap((Function<PathObservables.Event, ObservableSource<Envelope>>) event -> {
            Observable<String> v = Observable.merge(Nodes.path2NodeObservable(path, convertFunction), Nodes.path2NodeObservable(path, convertFunction));
            return v.flatMap((Function<String, ObservableSource<Envelope>>) s ->
                    allSubscribersObservable.map(webSocketSubscriber -> new Envelope(webSocketSubscriber, s))) ;
        });



        clientConnectObservable.subscribe(postOfficeService::sendAsJSON);
        folderCangedObservable.subscribe((postOfficeService::sendAsJSON));

    }
}
