package com.gft.challenge1.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class NodeRestController {

    @Autowired
    public NodeRestController() {
    }

    @RequestMapping(path = "/addFile")
    public String addNode(@RequestParam(value = "name") String path){
        return "Node \"" + path + "\"created successfully. ";
    }

    @RequestMapping(path = "/delete")
    public String delNode(@RequestParam(value = "name") String name){
        return "Node \"" + name + "\"deleted successfully.";
    }

    @RequestMapping()
    public String admin(){
        return "<html><body><h1>bla</h1><a href=\"/admin/add\">add</a></body></html>";
    }

}
