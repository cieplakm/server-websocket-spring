package com.gft.challenge1.server.config;

import com.gft.challenge1.server.config.WebSocketConfig;
import com.google.common.jimfs.Jimfs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@ComponentScan
public class MainConfig {
    @Bean
    Path myPath(){
        FileSystem fs = FileSystems.getDefault();//Jimfs.newFileSystem(com.google.common.jimfs.Configuration.windows());
        Path tempDirectory;
        tempDirectory = fs.getPath("/tmp");
        return tempDirectory;
    }

}
