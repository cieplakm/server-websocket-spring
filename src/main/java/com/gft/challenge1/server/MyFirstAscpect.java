package com.gft.challenge1.server;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyFirstAscpect {


    @Before("execution(* com.gft.challenge1.server.controllers.XXX.add())")
    public void before(){
        System.out.println("OOOOooooo!!!!!!!");
    }
}
