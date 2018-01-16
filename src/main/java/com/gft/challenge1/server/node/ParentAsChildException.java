package com.gft.challenge1.server.node;

public class ParentAsChildException extends Exception {
    public ParentAsChildException() {
        super("Parent can't be a child");
    }
}
