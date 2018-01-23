package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import com.gft.challenge1.server.node.SuperAlgorithm;
import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import lombok.SneakyThrows;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class RQWERTests {

    FileSystem fs;
    Path tempDirectory;

    private Observable<DirectoryChangedEvent> pathObservable;

    @Before
    @SneakyThrows
    public void setupTest(){
        fs = Jimfs.newFileSystem(Configuration.windows());
        tempDirectory = fs.getPath("C:/temp");
        Files.createDirectory(tempDirectory);

        pathObservable = new PathObservable(tempDirectory).getObservable();

    }

    @Test
    public void should(){
        String B = "B";
        String C = "C";
        String D = "D";
        String E = "E";

        Node<String> root = new NodeImpl<>();

        val child1 = new NodeImpl<>(root, B);
        val child2 = new NodeImpl<>(root, C);
        val child3 = new NodeImpl<>(root, D);
        val child4 = new NodeImpl<>(root, E);

        Node testNode = new NodeImpl<String>();

        Observable o = pathObservable.flatMap(new Function<DirectoryChangedEvent, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(DirectoryChangedEvent directoryChangedEvent) throws Exception {
                return Observable.just(testNode);
            }
        });

        try {
            Path hello = tempDirectory.resolve("test.txt");
            Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Observable oo = Observable.fromIterable(root).startWith(o);

        oo.blockingSubscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



    }
}
