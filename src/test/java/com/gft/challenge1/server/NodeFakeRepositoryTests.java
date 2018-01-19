package com.gft.challenge1.server;

import com.gft.challenge1.server.node.NodeFakeRepository;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class NodeFakeRepositoryTests {


    @Test
    public void shouldReturnNothing(){
        NodeFakeRepository nodeFakeRepository = new NodeFakeRepository();

        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(false);
    }

    @Test
    public void shouldAddNewNode(){
        NodeFakeRepository nodeFakeRepository = new NodeFakeRepository();
        nodeFakeRepository.addNewNode("Node1");

        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(true);
    }

    @Test
    public void shouldRemoveNode(){
        NodeFakeRepository nodeFakeRepository = new NodeFakeRepository();
        nodeFakeRepository.addNewNode("Node1");
        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(true);
        nodeFakeRepository.removeNode("Node1");
        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(false);
    }
}
