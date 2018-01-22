package com.gft.challenge1.server;

import com.gft.challenge1.server.node.SuperAlgorithm;
import com.gft.challenge1.server.node.Node;
import lombok.val;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import com.gft.challenge1.server.node.NodeImpl;

import java.util.Iterator;

public class SuperAlgorithmTests {

    @Test
    public void shouldBeEmpty(){
        Node<String> root = new NodeImpl<>();
        assertThat(SuperAlgorithm.convert2Iterator(root)).isEmpty();
    }

    @Test
    public void shouldReturnOneElement(){
        String B = "B";

        Node<String> root = new NodeImpl<>();
        val child1 = new NodeImpl<>(root, B);

        assertThat(SuperAlgorithm.convert2Iterator(root)).containsOnlyOnce(B);
    }

    @Test
    public void shouldIterateBFS(){
        String B = "B";
        String C = "C";
        String D = "D";

        Node<String> root = new NodeImpl<>();

        val child1 = new NodeImpl<>(root, B);
        val child2 = new NodeImpl<>(root, C);
        val child11 = new NodeImpl<>(child1, D);

        assertThat(SuperAlgorithm.convert2Iterator(root)).containsOnlyOnce(C,B,D);
    }
}
