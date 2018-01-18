package com.gft.challenge1.server.node;

import org.springframework.stereotype.Component;

@Component
public class NodeRepozitory {
    Node root = new NodeImpl();

    public void addNewNode(String name){
        new NodeImpl<>(root, name);
    }

    public Node getRoot(){
        return root;
    }
}
