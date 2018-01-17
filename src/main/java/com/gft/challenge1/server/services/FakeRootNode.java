package com.gft.challenge1.server.services;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import com.gft.challenge1.server.node.SuperAlgorithm;
import lombok.val;

import java.util.Iterator;

public class FakeRootNode {
    public static Iterator<Node> getIter() {
        Node<String> root = new NodeImpl<>();

        val child1 = new NodeImpl<>(root, "B-child1");
        val child2 = new NodeImpl<>(root, "C-child2");

        val child11 = new NodeImpl<>(child1, "D-child11");
        val child21 = new NodeImpl<>(child1, "E-child21");
        val child22 = new NodeImpl<>(child1, "F-child22");

        val child111 = new NodeImpl<>(child11, "G-child111");
        val child112 = new NodeImpl<>(child11, "H-child112");
        val child113 = new NodeImpl<>(child11, "I-child113");
        val child221 = new NodeImpl<>(child21, "J-child211");

        val child1111 = new NodeImpl<>(child111, "K-child1111");
        val child1112 = new NodeImpl<>(child111, "L-child1112");
        val child1131 = new NodeImpl<>(child113, "M-child1131");

        Iterator<Node> iter = SuperAlgorithm.convert2Iterator(root);
        return iter;
    }
}
