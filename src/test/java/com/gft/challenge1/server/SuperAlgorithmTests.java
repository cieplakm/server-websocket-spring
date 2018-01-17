package com.gft.challenge1.server;

import com.gft.challenge1.server.node.SuperAlgorithm;
import com.gft.challenge1.server.node.Node;
import lombok.val;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import com.gft.challenge1.server.node.NodeImpl;

import java.util.Iterator;

public class SuperAlgorithmTests {

//    @Test
//    public void shouldConvertTreeToNodeIterator(){
//
//        val root = new NodeImpl();
//
//        val child1 = new NodeImpl(root, "child1");
//        val child12 = new NodeImpl(child1, "child12");
//
//        assertThat( SuperAlgorithm.convert2Iterator(root) )
//                .containsOnlyOnce(child1.getPayload(), child12.getPayload());
//    }

    @Test
    public void shouldIterateBFS(){
        Node<String> root = new NodeImpl<>();

        val child1 = new NodeImpl<>(root, "B-child1");
        val child2 = new NodeImpl<>(root, "C-child2");

        val child11 = new NodeImpl<>(child1, "D-child11");
        val child21 = new NodeImpl<>(child1, "E-child21");
        val child22 = new NodeImpl<>(child1, "F-child22");

        val child111 = new NodeImpl<>(child11, "G-child111");
        val child112 = new NodeImpl<>(child11, "H-child112");
        val child113 = new NodeImpl<>(child11, "I-child113");
        val child221 = new NodeImpl<>(child21, "J-child211");

        val child1111 = new NodeImpl<>(child111, "K-child1111");
        val child1112 = new NodeImpl<>(child111, "L-child1112");
        val child1131 = new NodeImpl<>(child113, "M-child1131");

        Iterator<Node> iter = SuperAlgorithm.convert2Iterator(root);

        Node aChildB = iter.next();
        Node aChildC = iter.next();

        Node aChildD = iter.next();
        Node aChildE = iter.next();
        Node aChildF = iter.next();

        Node aChildG = iter.next();
        Node aChildH = iter.next();
        Node aChildI = iter.next();
        Node aChildJ = iter.next();

        Node aChildK = iter.next();
        Node aChildL = iter.next();
        Node aChildM = iter.next();

        assertThat(aChildB).isEqualTo(child1);
        assertThat(aChildC).isEqualTo(child2);

        assertThat(aChildD).isEqualTo(child11);
        assertThat(aChildE).isEqualTo(child21);
        assertThat(aChildF).isEqualTo(child22);

        assertThat(aChildG).isEqualTo(child111);
        assertThat(aChildH).isEqualTo(child112);
        assertThat(aChildI).isEqualTo(child113);
        assertThat(aChildJ).isEqualTo(child221);

        assertThat(aChildK).isEqualTo(child1111);
        assertThat(aChildL).isEqualTo(child1112);
        assertThat(aChildM).isEqualTo(child1131);


    }
}
