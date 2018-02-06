package com.gft.challenge1.server.path;

import com.sun.nio.file.SensitivityWatchEventModifier;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.*;

/**This class is to observe path changes.
 * Single thread is created when you instance.
 * Thread is staring when instance is created.
 * This class IS-A Disposable.
 *
 */
public final class PathObservables implements Disposable {

    private WatchService watchService;
    private PublishSubject<Event> pathObservable;
    private PathChangesObserverThread pathChangesObserverThread;
    private boolean isDisposed;


    public PathObservables(@NotNull Path directoryToObserve) throws IOException {
        pathObservable = PublishSubject.create();
        pathChangesObserverThread = new PathChangesObserverThread(directoryToObserve);
        watchService = createWatchServiceRegisteredTo(directoryToObserve);
        isDisposed = false;
        //cold observer
        pathChangesObserverThread.start();
    }

    public Observable<Event> observable(){
        return pathObservable;
    }

    private WatchService createWatchServiceRegisteredTo(Path path) throws IOException {
        FileSystem fs = path.getFileSystem();
        WatchService watchService = fs.newWatchService();

        path.register(watchService,
                new WatchEvent.Kind[]{StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE},
                SensitivityWatchEventModifier.HIGH);

        return watchService;
    }

    @Override
    public void dispose() {
        if (!isDisposed){
            isDisposed = true;
            pathChangesObserverThread.interrupt();
            pathObservable.onComplete();
        }
    }

    @Override
    public boolean isDisposed() {
        return isDisposed ;
    }

    public enum EventType {
        CREATED, UNKNOWN, DELETED
    }

    @Value
    public static class Event {
        private EventType type;
        private Path subject;
    }

    private class PathChangesObserverThread extends Thread {

        private Path directoryToObserve;

        private PathChangesObserverThread(@NotNull Path directoryToObserve) {
            this.directoryToObserve = directoryToObserve;
        }

        @Override
        public void run() {
            super.run();
            try {
                startListening4Change();
            } catch (InterruptedException e) {
                if (!isDisposed){
                    isDisposed = true;
                    pathObservable.onError(e);
                }
            }
        }

        private void startListening4Change() throws InterruptedException{
            while(!PathChangesObserverThread.this.isInterrupted()){
                //waiting for key
                WatchKey key = watchService.take();

                //emit every event
                for (WatchEvent event : key.pollEvents()){
                    EventType eventType = getEventType(event);
                    pathObservable.onNext(new Event(eventType, directoryToObserve.resolve((Path)event.context())));
                }

                boolean valid = key.reset();

                if (!valid) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private EventType getEventType(WatchEvent event) {
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
