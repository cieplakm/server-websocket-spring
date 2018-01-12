package com.gft.challenge1.server;
import com.gft.challenge1.server.model.Node;
import com.gft.challenge1.server.model.NodeImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class NodeImplTests {

    @Test
    public void shouldBeRootNode(){
        NodeImpl root = new NodeImpl(null);
        assertThat(root.isRoot()).isEqualTo(true);

        NodeImpl root2 = new NodeImpl();

        assertThat(root2.isRoot()).isEqualTo(true);
    }

    @Test
    public void shouldNotBeRootNode(){
        NodeImpl root = new NodeImpl();
        NodeImpl nodeImpl = new NodeImpl(root);

        assertThat(nodeImpl.isRoot()).isEqualTo(false);
    }

    @Test
    public void shouldNotHaveChildren(){
        NodeImpl root = new NodeImpl();
        NodeImpl nodeImpl = new NodeImpl(root);

        assertThat(nodeImpl.hasChildren()).isEqualTo(false);
    }

    @Test
    public void shouldHaveChildren(){
        NodeImpl root = new NodeImpl();
        NodeImpl nodeImpl = new NodeImpl(root);

        assertThat(root.hasChildren()).isEqualTo(true);
    }

    @Test
    public void shoulRemoveChildFromChildren(){
        NodeImpl root = new NodeImpl();
        NodeImpl nodeImpl = new NodeImpl(root);
        root.removeChild(nodeImpl);

        assertThat(root.hasChildren()).isEqualTo(false);
    }

    @Test
    public void shoulBeRootAfterRemoveFromRootsChildren(){
        NodeImpl root = new NodeImpl();
        NodeImpl nodeImpl = new NodeImpl(root);
        root.removeChild(nodeImpl);

        assertThat(nodeImpl.isRoot()).isEqualTo(true);
    }

    @Test
    public void shouldNotBeARootAfterAddToAnotherRoot(){
        NodeImpl root = new NodeImpl();
        NodeImpl anotherRoot = new NodeImpl();
        anotherRoot.addChild(root);

        assertThat(root.isRoot()).isEqualTo(false);
    }

    @Test
    public void shouldBeParentOfChild(){
        NodeImpl root = new NodeImpl();
        NodeImpl child = new NodeImpl(root);

        assertThat(child.getParent().equals(root)).isEqualTo(true);
    }

    @Test
    public void shouldBeChildOfParent(){
        Node root = new NodeImpl();
        Node child = new NodeImpl(root);

        assertThat(root).contains(child);

    }


    
}
