package com.gft.challenge1.server.node;

import com.gft.challenge1.server.services.OnNodeRepositoryChangedEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class NodeFakeRepository {
    private Node<String> root;
    private ApplicationEventPublisher publisher;

    public NodeFakeRepository() {
        root = new NodeImpl<>();
    }

    @Autowired
    public void setAppPublisher(@NotNull ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    public void addNewNode(String name){
        new NodeImpl<>(root, name);
        onRepositoryChanged();
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
                onRepositoryChanged();
                return;
            }
        }
    }

    private void onRepositoryChanged() {
        if (publisher != null)
            publisher.publishEvent(new OnNodeRepositoryChangedEvent());
    }
}
