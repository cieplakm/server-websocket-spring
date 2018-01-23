package com.gft.challenge1.server;

import com.google.common.collect.ImmutableList;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class PatchObserverTests {
    FileSystem fs;
    Path tempDirectory;

    @Before
    @SneakyThrows
    public void setupTest(){
        fs = Jimfs.newFileSystem(Configuration.windows());
        tempDirectory = fs.getPath("C:/temp");
        Files.createDirectory(tempDirectory);
    }

    @Test
    @SneakyThrows
    public void shouldEventBeNotNull(){
        PathObservable pathObservable = new PathObservable(tempDirectory);

        try {
            Path hello = tempDirectory.resolve("test.txt");
            Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DirectoryChangedEvent expected = pathObservable.getObservable().blockingFirst();

        Assertions.assertThat(expected).isNotNull();

    }
}