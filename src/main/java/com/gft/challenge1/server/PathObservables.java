package com.gft.challenge1.server;

import com.sun.nio.file.SensitivityWatchEventModifier;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.*;

public class PathObservables {

    public static Observable<Event> watch(@NotNull Path directoryToObserve) throws IOException {
        return new ObservableFactory(directoryToObserve).create();
    }

    enum EventType {
        CREATED, UNKNOWN, DELETED
    }

    @Value
    public static class Event {
        private EventType type;
        private Path subject;
    }

    private static class ObservableFactory {

        private WatchService watchService;
        private Path dir2Watch;

        private ObservableFactory(Path dir2Watch) throws IOException{
            this.dir2Watch = dir2Watch;
            FileSystem fs = dir2Watch.getFileSystem();
            watchService = fs.newWatchService();
            registerPathWithWatchService();
        }

        private Observable<Event> create(){
            return Observable
                    .create(this::startListening4Change)
                    .subscribeOn(Schedulers.computation());
        }

        private void registerPathWithWatchService() throws IOException {
                dir2Watch.register(watchService,
                        new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE,
                                StandardWatchEventKinds.ENTRY_DELETE,
                                StandardWatchEventKinds.ENTRY_MODIFY,
                                StandardWatchEventKinds.OVERFLOW},
                        SensitivityWatchEventModifier.HIGH);
        }

        private void startListening4Change(ObservableEmitter<Event> emitter) {
            while(true){

                WatchKey key;
                try {
                    //waiting for key
                    key = watchService.take();
                } catch (InterruptedException e) {
                    emitter.onError(e);
                    break;
                }

                for (WatchEvent event : key.pollEvents()){
                    EventType eventType = getEventType(event);
                    emitter.onNext(new Event(eventType, dir2Watch.resolve((Path)event.context())));
                }

                boolean valid = key.reset();

                if (!valid) {
                    return; // finish listening
                }
            }
        }

        private static EventType getEventType(WatchEvent event) {
            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE){
                return EventType.CREATED;
            }else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE){
                return EventType.DELETED;
            }else {
                return EventType.UNKNOWN;
            }
        }
    }
}
