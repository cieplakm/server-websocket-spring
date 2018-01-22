package com.gft.challenge1.server.node;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Algorithm iterate BFS method.
 * */
public class SuperAlgorithm {

    public static <E> Iterable<E> convert2Iterable(@NotNull Node<E> node){
        return () -> new SuperIterator<>(node);
    }

    static class SuperIterator<T> implements Iterator<T> {
        private Node<T> pointer;
        private Iterator<Node<T>> pointerIterator;
        private Queue<Node<T>> queue;
        private T next;

        private SuperIterator(Node<T> node) {
            queue = new LinkedList<>();
            pointer = node;
            pointerIterator = pointer.iterator();
            next = giveMeNextNode();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            T current = next;
            next = giveMeNextNode();

            return current;
        }

        private T giveMeNextNode(){
            if (pointerIterator.hasNext()){
                Node<T> node = pointerIterator.next();
                queue.add(node);

                return node.getPayload();
            }else {
                try {
                    pointer = queue.remove();
                }catch (NoSuchElementException exception){
                    return null;
                }
                pointerIterator = pointer.iterator();

                return giveMeNextNode();
            }
        }

    }

}
