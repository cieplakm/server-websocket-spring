package com.gft.challenge1.server.node;

import org.springframework.stereotype.Component;

@Component
public class NodeFakeRepository {
    private Node<String> root = new NodeImpl<>();

    public void addNewNode(String name){
        new NodeImpl<>(root, name);
    }

    public Node<String> getRoot(){
        return root;
    }
}
