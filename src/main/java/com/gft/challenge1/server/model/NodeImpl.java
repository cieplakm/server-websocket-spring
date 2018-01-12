package com.gft.challenge1.server.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** This class is data tree */
public class NodeImpl<T> implements Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;

    /** Constructor for root NodeImpl */
    public NodeImpl() {
        this(null);
    }

    /** Null @param means it is root */
    public NodeImpl(Node<T> parent) {
        children = new ArrayList<>();
        this.parent = parent;

        if (parent != null){
            try {
                parent.addChild(this);
            } catch (ParentAsChildException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addChild(Node<T> child) throws ParentAsChildException {
        if (child == parent) throw new  ParentAsChildException();

        children.add(child);

        NodeImpl<T> impl = (NodeImpl<T>)child;
        impl.setParent(this);
    }

    @Override
    public void removeChild(Node child){
        children.remove(child);

        NodeImpl impl = (NodeImpl)child;
        impl.removeParent();
    }

    private void removeParent() {
        parent = null;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public Node<T> getParent() {
        return parent;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean isRoot(){
        return parent == null;
    }

    @Override
    public boolean hasChildren(){
        return !children.isEmpty();
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeInterator();
    }

    /**Iterator thought children*/
    class NodeInterator implements Iterator<Node> {
        int pointer = 0;

        @Override
        public boolean hasNext() {
            return pointer < children.size();
        }

        @Override
        public Node next() {
            Node node = children.get(pointer);
            pointer++;
            return node;
        }

    }

}
