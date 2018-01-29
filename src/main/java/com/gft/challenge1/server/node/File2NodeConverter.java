package com.gft.challenge1.server.node;

import io.reactivex.Observable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class File2NodeConverter {

    public static <T> Observable<T> path2NodeObservable(Path path, ConvertFunction<T> convertFunction) throws IOException{
        return new NodeFactory<>(path, convertFunction).create();
    }

    private static class NodeFactory<T>{
        private Path rootPath;
        private ConvertFunction<T> convertFunction;

        private NodeFactory(Path rootPath, ConvertFunction<T> convertFunction) {
            this.rootPath = rootPath;
            this.convertFunction = convertFunction;
        }

        private Observable<T> create() throws IOException {
            Node<T> root = new NodeImpl<>();
            createTree(root, rootPath);
            Iterable<T> iterable = SuperAlgorithm.convert2Iterable(root);
            return Observable.fromIterable(iterable);
        }

        private Node<T> createTree(Node<T> root, Path rootPath) throws IOException{
            Node<T> result = null;

            Stream<Path> filesStream = Files.walk(rootPath).filter(path -> {
                //every files except root dir
                return !path.toString().equals(rootPath.toString());
            });

            for (Path path : filesStream.collect(Collectors.toList()) ){
                if (Files.isDirectory(path)){
                    //directory
                    result = createTree(new NodeImpl<>(root, convertFunction.getData(path) ), path);
                }else{
                    //file
                    result = new NodeImpl<>(root, convertFunction.getData(path) );
                }
            }

            return result;
        }
    }

    public interface ConvertFunction<E> {
        E getData(Path path);
    }


}
