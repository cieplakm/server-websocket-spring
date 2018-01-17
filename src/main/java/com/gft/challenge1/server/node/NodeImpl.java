package com.gft.challenge1.server.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/** This class is data tree.  */
public class NodeImpl<T> implements Node<T> {
    private Node parent;
    private List<Node> children;
    private T payload;


    /** Constructor for root Node */
    public NodeImpl() {
        this(null, (T) "root");
    }

    /** Null
     * @param parent means it is root.
     * */
    public NodeImpl(Node parent, T data) {
        children = new ArrayList<>();
        this.parent = parent;
        payload = data;

        if (parent != null)
            ((NodeImpl)parent).addChild(this);


    }

    private void addChild(Node node) {
        children.add(node);
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeIterator();
    }

    @Override
    public T getPayload() {
        return payload;
    }

    /**Iterator convert2Iterator children*/
    class NodeIterator implements Iterator<Node> {
        int pointer = 0;

        @Override
        public boolean hasNext() {
            return pointer < children.size() &&  !children.isEmpty();
        }

        @Override
        public Node next() {
            Node node = children.get(pointer);
            pointer++;
            return node;
        }

    }

}
