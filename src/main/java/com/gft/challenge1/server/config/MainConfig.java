package com.gft.challenge1.server.config;

import com.google.common.jimfs.Jimfs;
import com.google.common.jimfs.WatchServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
public class MainConfig {
    @Bean
    Path myPath(){
        com.google.common.jimfs.Configuration configuration = com.google.common.jimfs.Configuration.windows().toBuilder().setWatchServiceConfiguration(WatchServiceConfiguration.polling(1, TimeUnit.MICROSECONDS)).build();

        FileSystem fs = Jimfs.newFileSystem(configuration);
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
