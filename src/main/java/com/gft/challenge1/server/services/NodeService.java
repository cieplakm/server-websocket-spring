package com.gft.challenge1.server.services;

import com.gft.challenge1.server.node.NodeFakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeService {

    @Autowired
    NewsService newsService;
    @Autowired
    NodeFakeRepository nodeRepozitory;

    @RequestMapping(path = "/addnode")
    public String addNode(@RequestParam(value = "name", defaultValue = "New Node") String name){
        nodeRepozitory.addNewNode(name);
        newsService.sendWholeDataToAllObservers();
        return "Node \"" + name + "\"created successfully.";
    }
}
