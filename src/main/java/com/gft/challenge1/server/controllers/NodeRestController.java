package com.gft.challenge1.server.controllers;

import com.gft.challenge1.server.NodeFakeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class NodeRestController {
    private NodeFakeRepository fakeRepository;

    public NodeRestController(NodeFakeRepository fakeRepository) {
        this.fakeRepository = fakeRepository;
    }

    @RequestMapping(path = "/addFile")
    public String addNode(@RequestParam(value = "name") String path){

        fakeRepository.addNewNode(path);

        return "Node \"" + path + "\"created successfully. ";
    }

    @RequestMapping(path = "/delete")
    public String delNode(@RequestParam(value = "name") String name){
        fakeRepository.removeNode(name);
        return "Node \"" + name + "\"deleted successfully.";
    }

    @RequestMapping()
    public String admin(){
        return "<html><body><h1>bla</h1><a href=\"/admin/add\">add</a></body></html>";
    }

}
