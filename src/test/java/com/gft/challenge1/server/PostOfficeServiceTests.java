package com.gft.challenge1.server;

import com.gft.challenge1.server.services.Envelope;
import com.gft.challenge1.server.services.PostOfficeService;
import com.gft.challenge1.server.websockets.WebSocketSubscriber;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.WebSocketSession;

public class PostOfficeServiceTests {

    @Test
    public void shouldNotThrowAnyException(){
        PostOfficeService postOfficeService = new PostOfficeService();
        WebSocketSession webSocketSession = Mockito.mock(WebSocketSession.class);

        Assertions.assertThatCode(()-> postOfficeService.sendAsJSON(new Envelope<>(new WebSocketSubscriber(webSocketSession), "Data")))
                .doesNotThrowAnyException();
    }
}
