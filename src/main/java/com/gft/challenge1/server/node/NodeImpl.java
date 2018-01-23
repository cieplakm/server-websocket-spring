package com.gft.challenge1.server.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** This class is data tree.  */
public class NodeImpl<T> implements Node<T>{
    private List<Node<T>> children;
    private T payload;

    /** Constructor for root Node */
    public NodeImpl() {
        this(null, null);
    }

    /** Constructor for root Node */
    public NodeImpl(T data) {
        this(null, data);
    }

    /**
     * Null
     * @param parent means it is root.
     * */
    public NodeImpl(Node<T> parent, T data) {
        children = new ArrayList<>();
        payload = data;
        if (parent != null){
            ((NodeImpl<T>)parent).addChild(this);
        }
    }

    private void addChild(Node<T> node) {
        children.add(node);
    }

    @Override
    @org.jetbrains.annotations.NotNull
    public Iterator<Node<T>> iterator() {
        return new NodeIterator();
    }

    @Override
    public T getPayload() {
        return payload;
    }

    /**Iterator convert2Iterable children*/
    class NodeIterator implements Iterator<Node<T>> {
        int pointer = 0;
        private Node<T> lastReturnedNode;
        @Override
        public boolean hasNext() {
            return pointer < children.size() &&  !children.isEmpty();
        }

        @Override
        public Node<T> next() {
            lastReturnedNode = children.get(pointer);
            pointer++;
            return lastReturnedNode;
        }

        @Override
        public void remove() {
            children.remove(lastReturnedNode);
        }
    }

}
