package com.gft.challenge1.server;

import com.sun.nio.file.SensitivityWatchEventModifier;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**For every Path this class should be a Singleton*/
public class PathObservables {

    public static Observable<Event> watch(@NotNull Path directoryToObserve) {
        return new ObservableFactory(directoryToObserve).create();
    }

    enum EventType {
        CREATED, DELETED
    }

    @Value
    public static class Event {
        private EventType type;
        private File subject;
    }

    private static class ObservableFactory {

        private WatchService watchService;

        private Path dir2Watch;

        private ObservableFactory(Path dir2Watch) {
            this.dir2Watch = dir2Watch;
            FileSystem fs = dir2Watch.getFileSystem();
            try {
                watchService = fs.newWatchService();
                registerPathWithWatchService();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private Observable<Event> create(){
            return Observable.create((ObservableOnSubscribe<Event>) emitter -> {
                startListening4Change(emitter);
            }).subscribeOn(Schedulers.computation());
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
                //wait for key
                WatchKey key = null;
                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (WatchEvent event : key.pollEvents()){
                    EventType eventType = getEventType(event);
                    emitter.onNext(new Event(eventType, new File("/")));
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
            }
            return null;
        }
    }
}
