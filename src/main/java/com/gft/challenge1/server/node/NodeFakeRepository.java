package com.gft.challenge1.server.node;

import io.reactivex.Observable;
import io.reactivex.Observer;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class NodeFakeRepository extends Observable {
    private Node<String> root = new NodeImpl<>();
    private Observer observer;

    public void addNewNode(String name){
        new NodeImpl<>(root, name);

        if (observer != null)
            observer.onNext(null);
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

        if (observer != null)
            observer.onNext(null);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        this.observer = observer;
    }
}
