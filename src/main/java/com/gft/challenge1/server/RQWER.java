package com.gft.challenge1.server;


import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import com.gft.challenge1.server.services.NewsService;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.concurrent.TimeUnit;

/**I want this class to....*/

@Component
public class RQWER {

    private NewsService newsService;

    Observable<WatchEvent.Kind> pathObservable;
    @Autowired
    public RQWER(NewsService newsService) {
        this.newsService = newsService;
    }

    public void sadf(){
//        Path path = null; // First of all I need to know Directory!
//        Node root = new NodeImpl(); // Then I need to have root!
//
//
//
//
//            Observable observable4EveryClient =
//                    PathObservables
//                            .observable(path)
//                            .startWith(Observable.fromIterable(root));
//
//
//
//
//        Paths.get("/home/you/Desktop");
//
//        pathObservable
//                .debounce(1000, TimeUnit.MILLISECONDS)
//                .map(new Function<WatchEvent.Kind, Node>() {
//            @Override
//            public Node apply(WatchEvent.Kind kind) throws Exception {
//                return null;
//            }
//        });
    }
}
