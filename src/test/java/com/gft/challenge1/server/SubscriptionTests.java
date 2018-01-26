package com.gft.challenge1.server;

import com.gft.challenge1.server.services.Subscription;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.WebSocketSession;

import java.util.Timer;
import java.util.TimerTask;

public class SubscriptionTests {

    Subscription subscription;

    @Before
    public void setup(){
        subscription = new Subscription();
    }

    @Test
    public void shouldAddNewSubscriber(){
        WebSocketSubscriber fakeWebSubscriber = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));
        subscription.subscribe(fakeWebSubscriber);

        Assertions.assertThat(subscription.subscribers().blockingFirst()).isEqualTo(fakeWebSubscriber);
    }

    @Test
    @SneakyThrows
    public void shouldRemoveFromSubscription(){
        WebSocketSubscriber fakeWebSubscriber = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));
        subscription.subscribe(fakeWebSubscriber);
        subscription.unsubscribe(fakeWebSubscriber);

        Assertions.assertThat(subscription.subscribers().count().blockingGet()).isEqualTo(0L);
    }

    @Test
    public void shouldInformAboutNewlySubscriber(){
        WebSocketSubscriber fakeWebSubscriber = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //in minute will subscribe
                subscription.subscribe(fakeWebSubscriber);
            }
        },1000);

        Assertions.assertThat(subscription.newlyWebSubscriberObservable().blockingFirst()).isEqualTo(fakeWebSubscriber);

    }

    @Test
    @SneakyThrows
    public void shouldRemoveAllSubscribers(){
        WebSocketSubscriber fakeWebSubscriber = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));
        WebSocketSubscriber fakeWebSubscriber2 = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));
        WebSocketSubscriber fakeWebSubscriber3 = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));

        subscription.subscribe(fakeWebSubscriber);
        subscription.subscribe(fakeWebSubscriber2);
        subscription.subscribe(fakeWebSubscriber3);

        subscription.unsubscribeAll();

        Assertions.assertThat(subscription.subscribers().count().blockingGet()).isEqualTo(0);


        subscription.unsubscribe(fakeWebSubscriber3);
    }

    @Test
    public void shouldNotExceptionIfTryUnsubscribeIfNoSubscribers(){
        WebSocketSubscriber fakeWebSubscriber = new WebSocketSubscriber(Mockito.mock(WebSocketSession.class));

        subscription.unsubscribeAll();

        Assertions.assertThatCode(()-> subscription.unsubscribe(fakeWebSubscriber) ).doesNotThrowAnyException();
    }




}
