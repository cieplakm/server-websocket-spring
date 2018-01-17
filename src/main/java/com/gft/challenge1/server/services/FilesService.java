package com.gft.challenge1.server.services;

import com.gft.challenge1.server.ServerObservers;
import com.gft.challenge1.server.node.Node;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import java.util.Iterator;

@Service
public class FilesService {

    private ServerObservers observers;
    private Gson gson;

    public FilesService(ServerObservers observers, Gson gson) {
        this.observers = observers;
        this.gson = gson;
    }

    public void send(){
            Iterator<Node> iterator = FakeRootNode.getIter();
            while (iterator.hasNext()){
                Node node = iterator.next();
                observers.informObservers(gson.toJson(node.getPayload()));
            }
    }
}
