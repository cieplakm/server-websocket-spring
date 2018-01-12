package com.gft.challenge1.server.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** This class is ..... */
public class NodeImpl<T> implements Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;


    /** @param parent can be null and it's mean it is root NodeImpl */
    public NodeImpl(Node<T> parent) {
        children = new ArrayList<>();
        this.parent = parent;

        if (parent!=null){
            parent.addChild(this);
        }
    }

    /** Constructor for root NodeImpl */
    public NodeImpl() {
        this(null);
    }

    @Override
    public void addChild(Node<T> child) {
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
    public boolean isRoot(){
        return parent == null;
    }

    @Override
    public boolean hasChildren(){
        return !children.isEmpty();
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new NodeInterator(children);
//       return new Iterator<Node<T>>() {
//           int pointer = 0;
//           @Override
//           public boolean hasNext() {
//               return pointer < children.size();
//           }
//
//           @Override
//           public Node<T> next() {
//               Node<T> node = children.get(pointer);
//               pointer++;
//               return node;
//           }
//       };
    }


    class NodeInterator implements Iterator<Node<T>> {
        int pointer = 0;
        List<Node<T>> children;

        public NodeInterator(List<Node<T>> children) {
            this.children = children;
        }

        @Override
        public boolean hasNext() {
            return pointer < children.size();
        }

        @Override
        public Node<T> next() {
            Node<T> node = children.get(pointer);
            pointer++;
            return node;
        }

    }
}
