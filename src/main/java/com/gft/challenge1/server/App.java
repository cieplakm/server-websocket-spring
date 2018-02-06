package com.gft.challenge1.server;

import com.gft.challenge1.server.config.MainConfig;
import com.gft.challenge1.server.config.WebSocketConfig;
import com.gft.challenge1.server.services.PathMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@SpringBootApplication
@EnableAsync
@Configuration
@Component
@Import({MainConfig.class, WebSocketConfig.class})
public class App implements CommandLineRunner{
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @Autowired PathMonitorService pathMonitorService;
    @Autowired Path myPath;

    @Override
    public void run(String... strings) throws Exception {
        pathMonitorService.monitor(myPath);
    }
}
