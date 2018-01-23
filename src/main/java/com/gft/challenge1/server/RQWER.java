package com.gft.challenge1.server;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RQWER {

    public RQWER() {

        List<String> l = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            l.add("BLA"+i);
        }

        Observable observableFromList = Observable.fromArray(l.toArray());
        Observable observableInterval = Observable.interval(10, TimeUnit.SECONDS);

        Observable folder =  new PathObservable(FileSystems.getDefault().getPath("C:\\Users\\cieplaki\\Desktop\\mmc\\Projects\\Java projects\\server-websocket-spring")).getObservable();

        Observable folderToString = folder.map(new Function() {
            @Override
            public String apply(Object o) throws Exception {
                return "SSSSS!!!!!!!!!!!!";
            }
        });


        observableFromList.flatMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return null;
            }
        })


        Observable o1 = folderToString.startWith(observableFromList);




        o1.subscribe((Consumer<String>) strings -> {
            System.out.println(strings);
        });

    }
}
