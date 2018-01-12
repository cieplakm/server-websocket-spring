package com.gft.challenge1.server.model;

public class ParentAsChildException extends Exception {
    public ParentAsChildException() {
        super("Parent can't be a child");
    }
}
