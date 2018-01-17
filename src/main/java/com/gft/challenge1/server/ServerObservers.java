package com.gft.challenge1.server;

import com.gft.challenge1.server.websockets.Client;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**This class should be Singleton.
 * ServerObservers exist due to inform every registered Client */
@Component
public class ServerObservers {
    private List<Client> observers;

    public ServerObservers() {
        observers = new ArrayList<>();
    }

    public void register(Client observer){
        observers.add(observer);
    }

    public void unregister(WebSocketSession observer){
        for (Client client : observers){
            if (client.equals(observer)){
                observers.remove(client);
                return;
            }
        }
    }

    public void informObservers(String json){
        for (Client handler : observers){
            try {
                handler.sendTest(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
