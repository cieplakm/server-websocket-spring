package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Node;
import org.junit.Test;

import java.io.File;

public class File2NodeConverterTests {


    @Test
    public void testsome(){
        File file = new File("C:\\Users\\mzck\\Desktop\\server\\New folder");

        Node node = File2NodeConverter.convertFile2Node(file);
    }
}
