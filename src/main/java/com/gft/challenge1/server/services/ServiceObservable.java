package com.gft.challenge1.server.services;

import com.gft.challenge1.server.ServiceObserer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceObservable {
    public ServiceObservable() {
        serviceObserers = new ArrayList<>();
    }

    private List<ServiceObserer> serviceObserers;

    public void register(ServiceObserer obserer){
        serviceObserers.add(obserer);
    }

    public void unRegister(ServiceObserer obserer){
        serviceObserers.remove(obserer);
    }

    void inform(String string){
        for (ServiceObserer o : serviceObserers){
            o.response(string);
            System.out.println(o.toString());
            System.out.println("------------");
        }
    }
}
