package com.gft.challenge1.server;

import com.gft.challenge1.server.node.Node;
import com.gft.challenge1.server.node.NodeImpl;
import lombok.val;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class NodeImplTests {

    @Test
    public void shouldInformObserverThatNodeChanged(){

        Node<String> root = new NodeImpl<>();

        List<Node> emittedItems = new ArrayList<>();

        root.observable().subscribe(emittedItems::add);

        val child1 = new NodeImpl<>(root, "B-child1");
        val child2 = new NodeImpl<>(root, "C-child2");

        assertThat(emittedItems).contains(child1, child2);
    }

    @Test
    public void shouldInformObserverThatChildNodeChanged(){
        List<Node> emittedItems = new ArrayList<>();

        Node<String> root = new NodeImpl<>();
        val child1 = new NodeImpl<>(root, "B-child1");

        root.observable()
                .doOnNext(emittedItems::add)
                .subscribe();

        new NodeImpl<>(child1, "B-child11");

        // in emittedItems should be Node whose children changed
        assertThat(emittedItems).contains(child1);
    }
}
