package com.gft.challenge1.server.services;

import lombok.Data;

@Data
public class Message<T> {

    private T data;

    public Message( T data) {
        this.data = data;
    }

}
