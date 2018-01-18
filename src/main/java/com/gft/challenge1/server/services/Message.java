package com.gft.challenge1.server.services;

public class Message<T> {
    private static int previousID = 0;

    int id;
    String order;
    T data;

    public Message(String order, T data) {
        id = getNextId();
        this.order = order;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int getNextId() {
        previousID++;
        return previousID;
    }
}
