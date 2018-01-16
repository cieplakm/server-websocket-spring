package com.gft.challenge1.server;

import com.gft.challenge1.server.services.ServiceObservable;
import com.gft.challenge1.server.services.SomeService;
import com.vaadin.annotations.Push;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringUI
@Component
@Push
@Configuration
public class MyUI extends UI implements ServiceObserer{
    HorizontalLayout hl;
    Button btn;
    Label label;

    @Autowired
    ServiceObservable serviceObserver;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        hl = new HorizontalLayout();
        btn = new Button("click me");
        label = new Label("Not started");

        btn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                serviceObserver.unRegister(MyUI.this);
            }
        });

        hl.addComponent(btn);
        hl.addComponent(label);

        setContent(hl);

        serviceObserver.register(this);

        this.addDetachListener(new DetachListener() {
            @Override
            public void detach(DetachEvent detachEvent) {
                serviceObserver.unRegister(MyUI.this);
                System.out.println("DETATCHED!!!!");
            }
        });

    }

    public void update(String text){
        access(new Runnable() {
            @Override
            public void run() {
                label.setValue(text);
            }
        });
    }

    @Override
    public void response(String string) {
        update(string);
    }
}
