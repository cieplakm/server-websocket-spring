//package com.gft.challenge1.server;
//
//import com.google.common.collect.ImmutableList;
//import com.google.common.jimfs.Configuration;
//import com.google.common.jimfs.Jimfs;
//import lombok.SneakyThrows;
//import org.junit.Test;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.*;
//
//public class WatchServiceTests {
//
//    @Test @SneakyThrows
//    public void first(){
//
//        WatchService watchService = FileSystems.getDefault().newWatchService();
//
//        Path path = new File("C:\\Users\\mzck\\Desktop\\server").toPath();
//        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
//        WatchKey watchKey = watchService.poll();
//        WatchKey watchKey2 = watchService.take();
//        FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
//        Path foo = fs.getPath("/tmp");
//        Files.createDirectory(foo);
//
//        Path hello = foo.resolve("hello.txt"); // /foo/hello.txt
//        Files.write(hello, ImmutableList.of("hello world"), StandardCharsets.UTF_8);
//    }
//}
