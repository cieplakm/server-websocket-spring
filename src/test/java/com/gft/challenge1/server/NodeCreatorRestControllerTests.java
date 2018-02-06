package com.gft.challenge1.server;

import com.gft.challenge1.server.controllers.NodeCreatorRestController;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class NodeCreatorRestControllerTests {


    @Test
    @SneakyThrows
    public void shouldReturn201Status(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        Path tempDirectory = fs.getPath("C:\\temp");

        Files.createDirectory(tempDirectory);

        NodeCreatorRestController nodeCreatorRestController = new NodeCreatorRestController(tempDirectory);
        ResponseEntity<String> responseEntity = nodeCreatorRestController.addNode("name");

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @SneakyThrows
    public void shouldReturn403Status(){
        FileSystem fs = Jimfs.newFileSystem(Configuration.windows());
        Path tempDirectory = fs.getPath("C:\\temp");
        Path namePath = tempDirectory.resolve("name");

        Files.createDirectory(tempDirectory);
        Files.createDirectory(namePath);

        NodeCreatorRestController nodeCreatorRestController = new NodeCreatorRestController(tempDirectory);
        ResponseEntity<String> responseEntity = nodeCreatorRestController.addNode("name");

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
