package com.gft.challenge1.server;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.*;

public class PathObservable{
    public static Observable<DirectoryChangedEvent> observable(@NotNull Path directoryToObserve) {
        FileSystem fs = directoryToObserve.getFileSystem();
        WatchService watchService = null;
        Observable<DirectoryChangedEvent> observable = null;

        try {
            watchService = fs.newWatchService();
            directoryToObserve.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (observable == null) {
            WatchService finalWatchService = watchService;
            observable = Observable.create(emitter -> {
                for(;;){
                    WatchKey key;
                    try {
                        key = finalWatchService.take();
                    } catch (InterruptedException x) {
                        return;
                    }
                    emitter.onNext(new DirectoryChangedEvent());
                    key.pollEvents();
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            });
        }
        return observable.subscribeOn(Schedulers.computation());
    }

}
