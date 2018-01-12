package com.gft.challenge1.server.model;

public interface Node<T> extends Iterable<Node<T>>{

    void addChild(Node<T> child);

    void removeChild(Node<T> child);

    boolean isRoot();

    boolean hasChildren();

    Node<T> getParent();

}
