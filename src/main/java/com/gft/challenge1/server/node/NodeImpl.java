package com.gft.challenge1.server.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/** This class is data tree.  */
public class NodeImpl implements Node<String> {
    
    private Node parent;
    private List<Node> children;
    private String name;
    private String payload;
    private ConvertingAlgorithm convertingAlgorithm;

    /** Constructor for root Node */
    public NodeImpl() {
        this(null, "root");
    }

    /** Null
     * @param parent means it is root.
     * */
    public NodeImpl(Node parent, String name) {
        children = new ArrayList<>();
        this.parent = parent;
        this.name = name;
        payload = name;

        if (parent != null)
            ((NodeImpl)parent).addChild(this);

        convertingAlgorithm = new MySuperAlgorithm();
    }

    private void addChild(Node node) {
        children.add(node);
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeInterator();
    }

    @Override
    public String getPayload() {
        return payload;
    }

    @Override
    public Iterator<String> convertToIterator() {
        return convertingAlgorithm.convert2Iterator(this);
    }

    @Override
    public Stream convertToStream() {
        return convertingAlgorithm.convert2Stream(this);
    }

    @Override
    public ConvertingAlgorithm getConvertingAlgorithm() {
        return convertingAlgorithm;
    }

    /**Iterator convert2Iterator children*/
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
