package com.gft.challenge1.server.node;

import io.reactivex.Observable;
import lombok.NonNull;


/**Node iterate through his children**/
public interface Node<T> extends Iterable<Node<T>> {

    /**@return Yoy can get your payload data */
    @NonNull
    T getPayload();

}
