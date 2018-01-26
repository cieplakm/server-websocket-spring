package com.gft.challenge1.server.services;

import com.gft.challenge1.server.node.File2NodeConverter;
import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.PathObservables;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


/**This class is for create Observable which will be
 * creating Node from path and Watch service from path.*/
@Component
public class NodeXYZ {

    public Path all4ThisDirectory;
    private Subscription subscription;


    @Autowired
    public NodeXYZ(Path myPath, Subscription subscription) {
        this.all4ThisDirectory = myPath;
        this.subscription = subscription;
    }

    public void createNodeFromPath(Path path){
        //stream all of nodes
        Observable<String> nodeObservable = File2NodeConverter.convertFile2Node(path);


        Observable<PathObservables.Event> pathObservable;

        try {
            pathObservable = PathObservables.watch(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Observable<WebSocketSubscriber> newlySubscriberObservable = subscription.newlyWebSubscriberObservable();

        Observable<WebSocketSubscriber> allSubscribersObservable = subscription.subscribers();

        //every new client should get all files
        Observable.combineLatest(newlySubscriberObservable, nodeObservable, new BiFunction<WebSocketSubscriber, String, Envelope>() {
            @Override
            public Envelope apply(WebSocketSubscriber webSocketSubscriber, String s) throws Exception {
                return new Envelope(webSocketSubscriber, s);
            }
        });







    }
}
