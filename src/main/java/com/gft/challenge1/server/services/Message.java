package com.gft.challenge1.server.services;

public class Message<T> {
    private String order;
    private T data;

    public Message(String order, T data) {
        this.order = order;
        this.data = data;
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


}
