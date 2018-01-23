package com.gft.challenge1.server.node;

import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Algorithm iterate BFS method.
 * */
public class SuperAlgorithm {

    public static <E> Iterable<E> convert2Iterable(@NotNull Node<E> node){
        return () -> new SuperIterator<>(node);
    }

    static class SuperIterator<T> implements Iterator<T> {
        private Queue<Node<T>> candidatesNodes;

        private SuperIterator(Node<T> root) {
            candidatesNodes = new LinkedList<>();
            for (Node n : root){
                candidatesNodes.add(n);
            }
        }

        @Override
        public boolean hasNext() {
            return !candidatesNodes.isEmpty();
        }

        @Override
        public T next() {
            val result = candidatesNodes.remove();
            for (Node n : result){
                candidatesNodes.add(n);
            }
            return result.getPayload();
        }
    }
}


