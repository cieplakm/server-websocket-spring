package com.gft.challenge1.server;

import io.reactivex.Observable;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import static com.gft.challenge1.server.PathObservables.EventType.CREATED;
import static com.gft.challenge1.server.PathObservables.EventType.DELETED;

public class PatchObservablesTests {
    private Path tempDirectory;
    private Observable<PathObservables.Event> watcher;
    private FileSystem fs;

    @Before
    @SneakyThrows
    public void setupTest(){
        fs = Jimfs.newFileSystem(Configuration.windows());
        tempDirectory = fs.getPath("C:/temp");
        Files.createDirectory(tempDirectory);

        Path toDelete = tempDirectory.resolve("To delete");
        Files.createDirectory(toDelete);

        watcher = PathObservables.watch(tempDirectory);
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

}