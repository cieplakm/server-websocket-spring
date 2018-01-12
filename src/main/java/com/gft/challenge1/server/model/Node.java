package com.gft.challenge1.server.model;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/** This class is ..... */
public class Node<T> implements N<T> {
    T data;
    Node<T> parent;
    List<Node<T>> children;


    /** @param parent can be null and it's mean it is root Node */
    public Node(Node parent) {
        children = new ArrayList<>();
        this.parent = parent;

        if (parent!=null){
            parent.addChild(this);
        }
    }

    /** Constructor for root Node */
    public Node() {
        this(null);
    }

    @Override
    public void addChild(Node<T> child) {
        children.add(child);
        child.setParent(this);
    }
    @Override
    public void removeChild(Node<T> child){
        children.remove(child);
        child.removeParent();
    }

    private void removeParent() {
        parent = null;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public boolean isRoot(){
        return parent == null;
    }

    @Override
    public boolean hasChildren(){
        return !children.isEmpty();
    }

}
