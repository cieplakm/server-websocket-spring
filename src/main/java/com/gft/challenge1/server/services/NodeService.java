package com.gft.challenge1.server.services;

import com.gft.challenge1.server.node.NodeFakeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeService {
    private NodeFakeRepository fakeRepository;

    public NodeService(NodeFakeRepository fakeRepository) {
        this.fakeRepository = fakeRepository;
    }

    @RequestMapping(path = "/add")
    public String addNode(@RequestParam(value = "name", defaultValue = "New Node") String name){
        fakeRepository.addNewNode(name);
        return "Node \"" + name + "\"created successfully.";
    }

    @RequestMapping(path = "/delete")
    public String delNode(@RequestParam(value = "name") String name){
        fakeRepository.removeNode(name);
        return "Node \"" + name + "\"deleted successfully.";
    }

}
