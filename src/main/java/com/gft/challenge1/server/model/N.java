package com.gft.challenge1.server.model;

public interface N<T> {

    void addChild(Node<T> child);

    void removeChild(Node<T> child);

    boolean isRoot();

    boolean hasChildren();

    Node<T> getParent();

}
