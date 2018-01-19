package com.gft.challenge1.server.config;


import com.gft.challenge1.server.node.NodeFakeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainConfig {
    @Bean
    NodeFakeRepository fakeRepository(){
        return new NodeFakeRepository();
    }
}
