package com.gft.challenge1.server.node;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**I want to create algorithm converted tree structure
 * to iterable collection.
 * If you give me tree(of some data) I will return Iterator." */

public interface Node<T> extends Iterable<Node> {

    /**I you have Converting Algorithm you can convert Node to Iterator or stream*/
    ConvertingAlgorithm getConvertingAlgorithm();

    /**@return Yoy can get your payload data of any Node */
    T getPayload();

    /**You can convert Node to iterable*/
    Iterator<T> convertToIterator();

    /**You can convert Node to Stream*/
    Stream<T> convertToStream();


    /**You can write your own algorithm to converting*/
    interface ConvertingAlgorithm<T> {
        Iterator<T> convert2Iterator(Node node);
        Stream<T> convert2Stream(Node node);
    }

}
