package com.gft.challenge1.server.controllers;

import com.gft.challenge1.server.path.PathCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

@RestController
public class NodeRestController {

    private Path mypath;

    @Autowired
    public NodeRestController(Path mypath) {
        this.mypath = mypath;
    }

    @RequestMapping(path = "/addFile")
    public String addNode(@RequestParam(value = "name") String name)  {
        try {
            PathCreator.create(mypath, name);
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException){
                return "Node \"" + name + "\"aleady exist. Node did not created.";
            }
        }

        return "Node \"" + name + "\"created successfully. ";
    }

    @RequestMapping(path = "/delete")
    public String delNode(@RequestParam(value = "name") String name){
        return "Node \"" + name + "\"deleted successfully.";
    }

}
