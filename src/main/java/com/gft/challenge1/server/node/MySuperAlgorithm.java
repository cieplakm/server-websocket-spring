package com.gft.challenge1.server.node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


/** Assumptions: Node iterate through his children.
 * */
public class MySuperAlgorithm  {
    public Iterator convert2Iterator(Node node){
        return new SuperIterator(node);

    }

    class SuperIterator implements Iterator {
        private final Node rootNode;
        private Node pointer;
        private Queue queue;

        private Iterator pointerIterator;

        public SuperIterator(Node node) {
            rootNode = node;
            pointer = rootNode;
            queue = new LinkedList();

            pointerIterator = rootNode.iterator();

        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Node next() {
            return giveMeNextNode();
        }

        private Node giveMeNextNode(){
            if (pointerIterator.hasNext()){

                Node node = (Node) pointerIterator.next();
                addNodeToQueue(node);

                return node;
            }else {
                changePointerToFirstFromQueue();

                return giveMeNextNode();
            }
        }

        private void addNodeToQueue(Node node){
            queue.add(node);
        }

        private void changePointerToFirstFromQueue() {
            pointer = (Node) queue.remove();
            setPointerIterator();
        }

        private void setPointerIterator() {
            pointerIterator = pointer.iterator();
        }
        
    }



}
