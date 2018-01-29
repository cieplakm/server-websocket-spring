package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Path2NodeConverter;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import io.reactivex.Observable;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class Path2NodeConverterTests {

    Path2NodeConverter.ConvertFunction<String> convertFunction;


    @Before
    public void setupTest(){
        convertFunction = Path::toString;
    }

    @Test
    @SneakyThrows
    public void createSimpleNode(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        Path tempDirectory = fs.getPath("C:\\temp");
        Path newFolder = tempDirectory.resolve("New Folder");

        Files.createDirectory(tempDirectory);
        Files.createDirectory(newFolder);

        Observable nodeStream = Path2NodeConverter.path2NodeObservable(tempDirectory, convertFunction);
        Iterable itreable = nodeStream.blockingIterable();
        Assertions.assertThat(itreable).containsOnly("C:\\temp\\New Folder");
    }

    @Test
    @SneakyThrows
    public void createSimpleNodeAndFile(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        Path tempDirectory = fs.getPath("C:\\temp");
        Path newFolder = tempDirectory.resolve("New Folder");
        Path file = tempDirectory.resolve("file.txt");

        Files.createDirectory(tempDirectory);
        Files.createDirectory(newFolder);
        Files.write(file, new byte[1]);

        Observable nodeStream = Path2NodeConverter.path2NodeObservable(tempDirectory, convertFunction);
        Iterable<String> itreable = nodeStream.blockingIterable();
        Assertions.assertThat(itreable).containsOnly("C:\\temp\\New Folder", "C:\\temp\\file.txt");
    }

    @Test
    @SneakyThrows
    public void subFolderOfTempShouldHaveAFile(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        Path tempDirectory = fs.getPath("C:\\temp");
        Path newFolder = tempDirectory.resolve("New Folder");
        Path file = newFolder.resolve("file.txt");

        Files.createDirectory(tempDirectory);
        Files.createDirectory(newFolder);
        Files.write(file, new byte[1]);

        Observable nodeStream = Path2NodeConverter.path2NodeObservable(tempDirectory, convertFunction);
        Iterable itreable = nodeStream.blockingIterable();
        Assertions.assertThat(itreable).containsOnly("C:\\temp\\New Folder", "C:\\temp\\New Folder\\file.txt");
    }




}
