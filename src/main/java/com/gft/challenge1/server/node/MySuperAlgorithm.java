package com.gft.challenge1.server.node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


/** Assumptions: Node iterator iterate through his children.
 * Algorithm iterate BFS method.
 *
 * */
public class MySuperAlgorithm  {
    public static Iterator<Node> convert2Iterator(Node node){
        return new SuperIterator(node);
    }

    static class SuperIterator implements Iterator<Node> {
        private Node pointer;
        private Iterator pointerIterator;
        private Queue<Node> queue;


        private SuperIterator(Node node) {
            pointer = node;
            queue = new LinkedList<>();

            pointerIterator = pointer.iterator();
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
            pointer = queue.remove();
            setPointerIterator();
        }

        private void setPointerIterator() {
            pointerIterator = pointer.iterator();
        }

    }



}
