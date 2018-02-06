package com.gft.challenge1.server;

import com.gft.challenge1.server.config.MainConfig;
import com.gft.challenge1.server.config.WebSocketConfig;
import com.gft.challenge1.server.node.Nodes;
import com.gft.challenge1.server.path.PathObservables;
import com.gft.challenge1.server.services.Envelope;
import com.gft.challenge1.server.services.PostOfficeService;
import com.gft.challenge1.server.websockets.Subscription;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@SpringBootApplication
@EnableAsync
@Configuration
@Component
@Import({MainConfig.class, WebSocketConfig.class})
public class App implements CommandLineRunner{
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @Autowired Path myPath;
    @Autowired Subscription subscription;
    @Autowired PostOfficeService postOfficeService;

    @Override
    public void run(String... strings) throws Exception {
        Nodes.ConvertFunction<String> convertFunction = path1 -> path1.getFileName().toString();

        Observable<WebSocketSubscriber> newlySubscriberObservable = subscription.newlyWebSubscriberObservable();
        Observable<WebSocketSubscriber> allSubscribersObservable = subscription.subscribers();
        Observable<PathObservables.Event> eventObservable = new PathObservables(myPath).observable();



        //emit every file in root
        Observable <Envelope> clientConnectObservable = newlySubscriberObservable
                .flatMap((Function<WebSocketSubscriber, ObservableSource<Envelope>>) webSocketSubscriber ->
                        Nodes.path2NodeObservable(myPath, convertFunction).map(s -> new Envelope<>(webSocketSubscriber, s)));

        //emit single name of changed file in root
        Observable <Envelope> folderChangedObservable = eventObservable
                .flatMap( (Function<PathObservables.Event, ObservableSource<Envelope>>) event ->
                        allSubscribersObservable.map(webSocketSubscriber ->
                                new Envelope<>(webSocketSubscriber, event.getSubject().getFileName().toString()))
                );

        clientConnectObservable.subscribe(postOfficeService::sendAsJSON);
        folderChangedObservable.subscribe(postOfficeService::sendAsJSON);
    }

}
