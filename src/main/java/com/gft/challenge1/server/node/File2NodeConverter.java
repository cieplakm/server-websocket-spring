package com.gft.challenge1.server.node;

import io.reactivex.Observable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;


public class File2NodeConverter {

    public static Observable<String> convertFile2Node(Path path){
        Node root = new NodeImpl<>();

        try {
            createTree(root, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterable iterable = SuperAlgorithm.convert2Iterable(root);
        return Observable.fromIterable( iterable  );
    }

    private static Node createTree(Node root, Path rootPath) throws IOException{
        Node result = null;

        for (Path path : Files.walk(rootPath).collect(Collectors.toList()) ){
           result =  new NodeImpl<>(root, "Root");
//            if (Files.isDirectory(path)){
//                //directory
//                Node dirNode = createTree(root);
//                result = dirNode;
//            }else{
//                //file
//                Node fileNode = new NodeImpl<>(root);
//                result = fileNode;
//            }
        }

        return result;
    }


}
