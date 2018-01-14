package com.gft.challenge1.server.model;

public interface Node<T> extends Iterable<Node>{

    void addChild(Node<T> child) throws ParentAsChildException;

    void removeChild(Node<T> child);

    boolean isRoot();

    boolean hasChildren();

    Node<T> getParent();
    
    void setData(T data);

    T getData();

}
