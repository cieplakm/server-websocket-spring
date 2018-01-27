package com.gft.challenge1.server.services;

import com.gft.challenge1.server.node.File2NodeConverter;
import com.gft.challenge1.server.node.PathObservables;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;


/**This class is for create Observable which will be
 * creating Node from path and Watch service from path.*/
@Component
public class NodeXYZ {

    public Path all4ThisDirectory;
    private Subscription subscription;
    private PostOfficeService postOfficeService;

    @Autowired
    public NodeXYZ(Path myPath, Subscription subscription, PostOfficeService postOfficeService) {
        this.all4ThisDirectory = myPath;
        this.subscription = subscription;
        this.postOfficeService = postOfficeService;

        try {
            createNodeFromPath(myPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNodeFromPath(Path path) throws IOException {
        Observable<String> nodeObservable = File2NodeConverter.path2NodeObservable(path);
        Observable<PathObservables.Event> pathObservable = PathObservables.watch(path);
        Observable<WebSocketSubscriber> newlySubscriberObservable = subscription.newlyWebSubscriberObservable();
        Observable<WebSocketSubscriber> allSubscribersObservable = subscription.subscribers();


        Observable <Envelope> clientConnectObservable = newlySubscriberObservable
                .flatMap((Function<WebSocketSubscriber, ObservableSource<Envelope>>) webSocketSubscriber ->
                        nodeObservable.map(s -> new Envelope(webSocketSubscriber, s)));



        Observable <Envelope> folderCangedObservable = pathObservable.flatMap((Function<PathObservables.Event, ObservableSource<Envelope>>) event -> {
            Observable<String> v = Observable.merge(File2NodeConverter.path2NodeObservable(path), nodeObservable);
            return v.flatMap((Function<String, ObservableSource<Envelope>>) s ->
                    allSubscribersObservable.map(webSocketSubscriber -> new Envelope(webSocketSubscriber, s))) ;
        });




        clientConnectObservable.subscribe(postOfficeService::sendAsJSON);
        folderCangedObservable.subscribe((postOfficeService::sendAsJSON));


    }
}
