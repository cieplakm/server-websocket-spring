package com.gft.challenge1.server.services;

import com.gft.challenge1.server.MyUI;
import com.gft.challenge1.server.TimeListener;
import com.gft.challenge1.server.UIChangeListenr;
import com.vaadin.ui.UI;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SomeService {


    ServiceObservable serviceObserver;

    int i=0;


    public SomeService(ServiceObservable serviceObserver) {
        this.serviceObserver = serviceObserver;
    }


    @Async
    public void run() {


        while(true){
            i++;

            serviceObserver.inform("Update " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public int getI() {
        return i;
    }
}
