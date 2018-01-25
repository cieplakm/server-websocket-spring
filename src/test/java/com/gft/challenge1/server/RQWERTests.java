package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class RQWERTests {
//
//    FileSystem fs;
//    Path tempDirectory;
//
//    private Observable<DirChangedEvent> pathObservable;
//
//    @Before
//    @SneakyThrows
//    public void setupTest(){
//        fs = Jimfs.newFileSystem(Configuration.windows());
//        tempDirectory = fs.getPath("C:/temp");
//        Files.createDirectory(tempDirectory);
//
//        pathObservable = PathObservables.observable(tempDirectory);
//
//    }
//
//    @Test
//    public void should(){
//        String B = "B";
//
//        Node<String> root = new NodeImpl<>();
//        val child1 = new NodeImpl<>(root, B);
//        val testNode = new NodeImpl<String>();
//
//
//        Observable<Node<String>> path2Node = pathObservable.map(new Function<DirChangedEvent, Node<String>>() {
//            @Override
//            public Node<String> apply(DirChangedEvent directoryChangedEvent) throws Exception {
//                return testNode;
//            }
//        });
//        Observable<Node<String>> fromIterator = Observable.fromIterable(root);
//        Observable<Node<String>> nodeFromListAndEvent = path2Node.startWith(fromIterator);
//
//        try {
//            Path hello = tempDirectory.resolve("test.txt");
//            Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        Iterable<Node<String>> actual = nodeFromListAndEvent.blockingIterable();
//
//
//
//
//    }
}
