package com.gft.challenge1.server.node;

import lombok.NonNull;
import java.util.Iterator;
import java.util.stream.Stream;


/**Node iters through his children**/
public interface Node<T> extends Iterable<Node> {

    /**@return Yoy can get your payload data of any Node */
    @NonNull
    T getPayload();

}
