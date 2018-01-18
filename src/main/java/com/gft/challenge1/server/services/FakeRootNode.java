package com.gft.challenge1.server.services;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import com.gft.challenge1.server.node.SuperAlgorithm;
import lombok.val;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FakeRootNode {

    public static Iterator<String> much() {
        List<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
      return l.iterator();
    }


    public static Iterator<String> less() {
        List<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        return l.iterator();
    }
}
