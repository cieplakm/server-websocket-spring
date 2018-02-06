package com.gft.challenge1.server.config;

import com.google.common.jimfs.Jimfs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@ComponentScan
public class MainConfig {
    @Bean
    Path myPath(){
        FileSystem fs = Jimfs.newFileSystem(com.google.common.jimfs.Configuration.windows());
        Path tempDirectory;
        tempDirectory = fs.getPath("C:\\temp");
        try {
            Files.createDirectory(tempDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempDirectory;
    }

}
