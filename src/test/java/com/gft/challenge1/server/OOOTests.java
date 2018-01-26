package com.gft.challenge1.server;

import com.gft.challenge1.server.config.MainConfig;
import com.gft.challenge1.server.config.WebSocketConfig;
import com.gft.challenge1.server.services.NodeXYZ;
import io.reactivex.functions.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={App.class})

@Configuration
public class OOOTests {

    @Autowired
    NodeXYZ nodeXYZ;


    @Test
    public void oo(){
        io.reactivex.Observable<Boolean> observable = nodeXYZ.createNodeFromPath(nodeXYZ.all4ThisDirectory);

    }
}
