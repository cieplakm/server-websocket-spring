package com.gft.challenge1.server;

import com.gft.challenge1.server.services.ServiceObservable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringConfig {

    @Bean
    public TimeListener timeListener() {
        return (timeleft)->  System.out.println("Left " + timeleft);
    }



}
