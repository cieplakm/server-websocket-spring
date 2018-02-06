package com.gft.challenge1.server;

import com.gft.challenge1.server.path.PathObservables;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import io.reactivex.Observable;
import lombok.SneakyThrows;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static com.gft.challenge1.server.path.PathObservables.EventType.CREATED;
import static com.gft.challenge1.server.path.PathObservables.EventType.DELETED;

public class PatchObserverTests {
    private Path tempDirectory;
    private Observable<PathObservables.Event> watcher;
    private PathObservables pathObservables;

    @Before
    @SneakyThrows
    public void setupTest(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        tempDirectory = fs.getPath("C:/temp");
        Files.createDirectory(tempDirectory);

        Path toDelete = tempDirectory.resolve("To delete");
        Files.createDirectory(toDelete);

        pathObservables = new PathObservables(tempDirectory);
        watcher = pathObservables.observable();
    }

    @Test
    @SneakyThrows
    public void shouldNotifyAboutSimpleFile(){
        Path hello = tempDirectory.resolve("New Folder");
        Files.createDirectory(hello);

        val expected = watcher.blockingFirst();
        Assertions.assertThat(expected).isNotNull();
    }

    @Test
    @SneakyThrows
    public void shouldReturnCreateEvent(){
        Path createdDir = tempDirectory.resolve("new dir");
        Files.createDirectory(createdDir);

        val createExpected =  watcher.blockingFirst();
        Assertions.assertThat(createExpected.getType()).isEqualTo(CREATED);
    }

    @Test
    @SneakyThrows
    public void shouldReturnDeleteEvent(){
        Path dirToDelete = tempDirectory.resolve("To delete");

        Files.delete(dirToDelete);
        val createExpected =  watcher.blockingFirst();
        Assertions.assertThat(createExpected.getType()).isEqualTo(DELETED);

    }

    @Test
    @SneakyThrows
    public void shouldReturnPathToCreatedFolder(){
        Path superDir = tempDirectory.resolve("Super dir");

        Files.createDirectory(superDir);
        val createExpected =  watcher.blockingFirst();

        Assertions.assertThat(createExpected.getSubject()).isEqualTo(superDir);
    }

    @Test
    @SneakyThrows
    public void shouldBeDisposeAfterDispose(){
        PathObservables pathObservables = new PathObservables(tempDirectory);

        Assertions.assertThat(pathObservables.isDisposed()).isEqualTo(false);
        pathObservables.dispose();
        Assertions.assertThat(pathObservables.isDisposed()).isEqualTo(true);
    }

    @Test
    @SneakyThrows
    public void shouldNotEmitAnyItemAfterDispose(){
        PathObservables pathObservables = new PathObservables(tempDirectory);
        pathObservables.dispose();

        Path superDir = tempDirectory.resolve("Super dir");
        Files.createDirectory(superDir);

        Assertions.assertThatThrownBy(()->pathObservables.observable().blockingFirst())
        .isInstanceOf(NoSuchElementException.class);
    }

}