package com.gft.challenge1.server.node;

import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class NodeFakeRepository {
    private Node<String> root = new NodeImpl<>();

    public void addNewNode(String name){
        new NodeImpl<>(root, name);
    }

    public Node<String> getRoot(){
        return root;
    }

    public void removeNode(String name) {
        Iterator<Node> nodeIterator = root.iterator();

        while(nodeIterator.hasNext()){
            Node node = nodeIterator.next();

            if (node.getPayload().equals(name)){
                nodeIterator.remove();
                return;
            }
        }




    }
}
