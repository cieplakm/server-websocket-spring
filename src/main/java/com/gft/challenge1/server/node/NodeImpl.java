package com.gft.challenge1.server.node;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** This class is data tree.  */
public class NodeImpl<T> implements Node<T>{
    private Node parent;
    private List<Node> children;
    private T payload;
    private PublishSubject<Node> subject;


    /** Constructor for root Node */
    public NodeImpl() {
        this(null, null);
    }

    /** Null
     * @param parent means it is root.
     * */
    public NodeImpl(Node parent, T data) {
        children = new ArrayList<>();
        this.parent = parent;
        payload = data;

        subject = PublishSubject.create();

        if (parent != null){
            ((NodeImpl)parent).addChild(this);
        }

    }

    private void addChild(Node node) {
        children.add(node);
        informObserversAboutChange(node);
    }

    private void informObserversAboutChange(Node node) {
        //inform observer subscribed only to this Node
        if (subject.hasObservers()) {
            subject.onNext(node);
        }

        //inform every observer who subscribed to parent Node
        if (parent != null){
            informParentAboutChanges();
        }
    }

    @SuppressWarnings("unchecked")
    private void informParentAboutChanges(){
        Subject<Node> subject2 = (Subject<Node>) parent.observable();
        subject2.onNext(this);
    }

    @Override
    public Observable<Node> observable() {
        return subject;
    }

    @Override
    @org.jetbrains.annotations.NotNull
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
