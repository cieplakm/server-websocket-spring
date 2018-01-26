package com.gft.challenge1.server;

import com.gft.challenge1.server.node.File2NodeConverter;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.File;
import com.google.common.jimfs.Jimfs;
import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class File2NodeConverterTests {

//    @Test
//    @SneakyThrows
//    public void createSimpleNode(){
//        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
//        Path tempDirectory = fs.getPath("C:/temp");
//        Path newFolder = tempDirectory.resolve("New Folder");
//
//        Files.createDirectory(tempDirectory);
//        Files.createDirectory(newFolder);
//
//        File2NodeConverter.createObservableFromPath(tempDirectory);
//    }
}
