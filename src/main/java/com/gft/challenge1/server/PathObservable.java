package com.gft.challenge1.server;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.*;

public class PathObservable{
    private WatchService  watchService;
    private Observable<DirectoryChangedEvent> observable;

    public PathObservable(@NotNull Path directoryToObserve) {
        FileSystem fs = directoryToObserve.getFileSystem();

        try {
            watchService = fs.newWatchService();
            directoryToObserve.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        observable = Observable.create(emitter -> {
            for(;;){
                WatchKey key;
                try {
                    key = watchService.take();
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

    public Observable<DirectoryChangedEvent> getObservable() {
        return observable.subscribeOn(Schedulers.computation());
    }
}
