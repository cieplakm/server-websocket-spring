package com.gft.challenge1.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.*;

//this annotation is 4 Autowire ApplicationEventPublisher
@RunWith(SpringJUnit4ClassRunner.class)
public class NodeFakeRepositoryTests {


//
//    @Test
//    public void shouldReturnNothing(){
//        NodeFakeRepository nodeFakeRepository = new NodeFakeRepository();
//        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(false);
//    }
//
//    @Test
//    public void shouldAddNewNode(){
//        NodeFakeRepository nodeFakeRepository = new NodeFakeRepository();
//        nodeFakeRepository.addNewNode("Node1");
//        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(true);
//    }
//
//    @Test
//    public void shouldRemoveNode(){
//        NodeFakeRepository nodeFakeRepository = new NodeFakeRepository();
//        nodeFakeRepository.addNewNode("Node1");
//        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(true);
//        nodeFakeRepository.removeNode("Node1");
//        assertThat(nodeFakeRepository.getRoot().iterator().hasNext()).isEqualTo(false);
//    }
}
