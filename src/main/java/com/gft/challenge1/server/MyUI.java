package com.gft.challenge1.server;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class MyUI extends UI{
    VerticalLayout verticalLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        verticalLayout = new VerticalLayout();

        setContent(verticalLayout);
    }

    public void append(String string){
        verticalLayout.addComponent(new Label(string));
    }

}
