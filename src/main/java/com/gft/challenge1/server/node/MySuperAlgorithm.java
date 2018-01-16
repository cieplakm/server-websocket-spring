package com.gft.challenge1.server.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class MySuperAlgorithm<E> implements Node.ConvertingAlgorithm<E> {

    public List<E> convert2List(Node<String> node){
        List<E> list = new ArrayList<>();

        for (Object chid : node) {
            Node childNode = (Node) chid;

            if (childNode != null) {
                list.add((E) childNode.getPayload());
            }

            if (childNode.iterator().hasNext()){
                List<E> list1 = convert2List(childNode);
                list.addAll(list1);
            }
        }

        return list;
    }

    @Override
    public Iterator<E> convert2Iterator(Node node) {
        return convert2List(node).iterator();
    }

    @Override
    public Stream<E> convert2Stream(Node node) {
        return null;
    }

}
