package com.gft.challenge1.server.node;

import io.reactivex.Observable;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Nodes {

    public static <E> Iterable<E> convert2Iterable(@NotNull Node<E> node){
        return () -> new SuperIterator<>(node);
    }

    public static <T> Observable<T> path2NodeObservable(Path path, ConvertFunction<T> convertFunction) throws IOException{
        return new NodeObservableFactory<>(path, convertFunction).create();
    }

    public interface ConvertFunction<E> {
        E getData(Path path);
    }

    static class SuperIterator<T> implements Iterator<T> {
        private Queue<Node<T>> candidatesNodes;

        private SuperIterator(Node<T> root) {
            candidatesNodes = new LinkedList<>();
            for (Node<T> n : root){
                candidatesNodes.add(n);
            }
        }

        @Override
        public boolean hasNext() {
            return !candidatesNodes.isEmpty();
        }

        @Override
        public T next() {
            val result = candidatesNodes.remove();
            for (Node<T> n : result){
                candidatesNodes.add(n);
            }
            return result.getPayload();
        }
    }

    private static class NodeObservableFactory<T>{
        private Path rootPath;
        private ConvertFunction<T> convertFunction;

        private NodeObservableFactory(Path rootPath, ConvertFunction<T> convertFunction) {
            this.rootPath = rootPath;
            this.convertFunction = convertFunction;
        }

        private Observable<T> create() throws IOException {
            Node<T> root = new NodeImpl<>();
            createTree(root, rootPath);
            Iterable<T> iterable = convert2Iterable(root);
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
}
