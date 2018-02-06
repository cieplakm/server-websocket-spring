package com.gft.challenge1.server.controllers;

import com.gft.challenge1.server.path.PathCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

@RestController
public class NodeCreatorRestController {

    private Path mypath;

    @Autowired
    public NodeCreatorRestController(Path mypath) {
        this.mypath = mypath;
    }

    @RequestMapping(path = "/addFile")
    public ResponseEntity<String> addNode(@RequestParam(value = "name") String name)  {
        try {
            PathCreator.create(mypath, name);
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException){
                return new ResponseEntity<>("Node \"" + name + "\"aleady exist. Node did not created.", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>("Node \"" + name + "\"created successfully. ", HttpStatus.CREATED);
    }

}
