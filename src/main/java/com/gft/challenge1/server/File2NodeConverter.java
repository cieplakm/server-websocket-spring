package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;

import java.io.File;
import java.util.Objects;

public class File2NodeConverter {
    public static Node<File> convertFile2Node(File file){
        Node<File> root = new NodeImpl<>(file);
        createTree(root);
        return  root;
    }

    private static void createTree(Node<File> node){
        for (File f : node.getPayload().listFiles()){
            if (f.isDirectory()){
                createTree(new NodeImpl<>(node, f));
            }else {
                new NodeImpl<>(node, f);
            }
        }
    }
}
