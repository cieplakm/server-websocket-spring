package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Nodes;
import com.gft.challenge1.server.node.Node;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.gft.challenge1.server.node.NodeImpl;

public class SuperAlgorithmTests {

    @Test
    public void shouldBeEmpty(){
        Node<String> root = new NodeImpl<>();
        Assertions.assertThat(Nodes.convert2Iterable(root)).isEmpty();
    }

    @Test
    public void shouldReturnOneElement(){
        String B = "B";

        Node<String> root = new NodeImpl<>();
        val child1 = new NodeImpl<>(root, B);

        Assertions.assertThat(Nodes.convert2Iterable(root)).containsOnlyOnce(B);
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

        Assertions.assertThat(Nodes.convert2Iterable(root)).containsOnlyOnce(C,B,D);
    }

    @Test
    public void shouldContainsOnlyOnce(){
        String B = "B";
        String C = "C";
        String D = "D";
        String E = "E";

        Node<String> root = new NodeImpl<>();

        val child1 = new NodeImpl<>(root, B);
        val child2 = new NodeImpl<>(child1, C);
        val child3 = new NodeImpl<>(child1, D);
        val child4 = new NodeImpl<>(child1, E);

        Assertions.assertThat(Nodes.convert2Iterable(root)).containsOnlyOnce(C,B,D,E);
    }
}
