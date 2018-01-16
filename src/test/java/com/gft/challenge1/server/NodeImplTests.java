package com.gft.challenge1.server;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import com.gft.challenge1.server.node.ParentAsChildException;

import java.util.Iterator;


public class NodeImplTests {

    @Test
    public void shouldConvertTreeToNodeIteratorAndNotContainsRootNode(){

        val root = new NodeImpl();

        val child1 = new NodeImpl(root, "child1");
        val child2 = new NodeImpl(root, "child2");
        val child3 = new NodeImpl(root, "child3");
        val child4 = new NodeImpl(root, "child4");

        val child11 = new NodeImpl(child1, "child11");
        val child12 = new NodeImpl(child1, "child12");
        val child13 = new NodeImpl(child1, "child13");

        val child31 = new NodeImpl(child3, "child31");
        val child32 = new NodeImpl(child3, "child32");

        val child41 = new NodeImpl(child4, "child41");


//        for ( Object o : root ){
//            System.out.println( "Node item: " + ( (NodeImpl) o).name );
//        }

        assertThat(root.convertToIterator())
                .containsOnlyOnce(child1.getPayload(),
                        child2.getPayload(),
                        child3.getPayload(),
                        child4.getPayload(),
                        child11.getPayload(),
                        child12.getPayload(),
                        child13.getPayload(),
                        child31.getPayload(),
                        child32.getPayload(),
                        child41.getPayload()
                );

        assertThat(root.convertToIterator())
                .doesNotContain(root.getPayload());

        assertThat(child1.convertToIterator())
                .containsOnlyOnce(
                child11.getPayload(),
                        child12.getPayload(),
                        child13.getPayload() );

        assertThat(child1.convertToIterator())
                .doesNotContain(root.getPayload(),
                        child1.getPayload(),
                        child2.getPayload(),
                        child3.getPayload(),
                        child4.getPayload(),
                        child31.getPayload(),
                        child32.getPayload(),
                        child41.getPayload()
                );

        assertThat(child11.convertToIterator())
                .isEmpty();

    }




}
