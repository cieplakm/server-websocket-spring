package com.gft.challenge1.server.services;

import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class Subscription {

    private Set<WebSocketSubscriber> webSocketSubscribers;
    private Observable<WebSocketSubscriber> subscriberObservable;
    private PublishSubject<WebSocketSubscriber> newWebSubscriberSubject;

    public Subscription() {
        this.webSocketSubscribers = new HashSet<>();
        subscriberObservable = Observable.fromIterable(webSocketSubscribers);
        newWebSubscriberSubject = PublishSubject.create();
    }

    public void subscribe(WebSocketSubscriber webSocketSubscriber){
        webSocketSubscribers.add(webSocketSubscriber);
        //inform about new WebSocketSubscriber
        newWebSubscriberSubject.onNext(webSocketSubscriber);
    }

    public Observable<WebSocketSubscriber> subscribers(){
        return subscriberObservable;
    }

    public Observable<WebSocketSubscriber> newlyWebSubscriberObservable(){
        return newWebSubscriberSubject;
    }

    public void unsubscribe(WebSocketSubscriber webSocketSubscriber2UnSubscribe) throws NoSuchElementException {
        subscribers()
                .filter(webSocketSubscriber -> webSocketSubscriber.equals(webSocketSubscriber2UnSubscribe))
                .singleOrError()
                .subscribe(new SingleObserver<WebSocketSubscriber>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(WebSocketSubscriber webSocketSubscriber) {

                        webSocketSubscribers.remove(webSocketSubscriber);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void unsubscribeAll(){
        webSocketSubscribers.clear();
    }
}
