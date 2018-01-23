package com.gft.challenge1.server;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class PatchObserverTests {
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
    public void shouldEventBeNotNull(){
        Observable<DirectoryChangedEvent> pathObservable = PathObservable.observable(tempDirectory);
        Path hello = tempDirectory.resolve("test.txt");
        Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);

        DirectoryChangedEvent expected = pathObservable.blockingFirst();
        Assertions.assertThat(expected).isNotNull();
    }
}