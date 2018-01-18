package com.gft.challenge1.server.config;


import com.gft.challenge1.server.node.NodeRepozitory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainConfig {
    @Bean
    NodeRepozitory nodeRepozitory(){
        return new NodeRepozitory();
    }
}
