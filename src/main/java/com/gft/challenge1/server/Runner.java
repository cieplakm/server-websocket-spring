package com.gft.challenge1.server;

import com.gft.challenge1.server.services.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationListener<ApplicationReadyEvent>
{
    @Autowired
    SomeService someService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        someService.run();
    }
}
