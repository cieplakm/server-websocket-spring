package com.gft.challenge1.server;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.omg.CORBA.OBJ_ADAPTER;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;

import static com.gft.challenge1.server.PathObservables.EventType.CREATED;
import static com.gft.challenge1.server.PathObservables.EventType.DELETED;

public class PatchObservablesTests {
    private Path tempDirectory;

    @Before
    @SneakyThrows
    public void setupTest(){

        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        tempDirectory = fs.getPath("C:/temp");
        Files.createDirectory(tempDirectory);
    }

    @Test
    @SneakyThrows
    public void shouldNotifyAboutSimpleFile(){
        val watcher = PathObservables.watch(tempDirectory);

        Path hello = tempDirectory.resolve("addedDir2");

        try {
            Files.createDirectory(hello);
        } catch (IOException e) {
            e.printStackTrace();
        }

        val expected = watcher.blockingFirst();

        Assertions.assertThat(expected).isNotNull();
    }

    @Test
    @SneakyThrows
    public void shouldReturnCreateEvent(){
        val watcher = PathObservables.watch(tempDirectory);
        Path createdDir = tempDirectory.resolve("new dir");

        Files.createDirectory(createdDir);
        val createExpected =  watcher.blockingFirst();
        Assertions.assertThat(createExpected.getType()).isEqualTo(CREATED);

    }

    @Test
    @SneakyThrows
    public void shouldReturnDeleteEvent(){
        Path dirToDelete = tempDirectory.resolve("dirTodelete");
        Files.createDirectory(dirToDelete);

        val watcher = PathObservables.watch(tempDirectory);
        Files.delete(dirToDelete);
        val createExpected =  watcher.blockingFirst();
        Assertions.assertThat(createExpected.getType()).isEqualTo(DELETED);

    }


}