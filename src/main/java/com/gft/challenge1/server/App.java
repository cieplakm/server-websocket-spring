package com.gft.challenge1.server;

import com.gft.challenge1.server.config.MainConfig;
import com.gft.challenge1.server.config.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Configuration
@Import({MainConfig.class, WebSocketConfig.class})
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
