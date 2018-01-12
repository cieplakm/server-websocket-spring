package com.gft.challenge1.server;
import com.gft.challenge1.server.model.Node;
import com.gft.challenge1.server.model.NodeImpl;
import com.gft.challenge1.server.model.ParentAsChildException;
import org.junit.Test;

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
        Node root = new NodeImpl();
        Node anotherRoot = new NodeImpl();
        try {
            anotherRoot.addChild(root);
        } catch (ParentAsChildException e) {
            e.printStackTrace();
        }

        assertThat(root.isRoot()).isEqualTo(false);
    }

    @Test
    public void shouldBeParentOfChild(){
        Node root = new NodeImpl();
        Node child = new NodeImpl(root);

        assertThat(child.getParent().equals(root)).isEqualTo(true);
    }

    @Test
    public void shouldBeChildOfParent() throws ParentAsChildException {
        Node<String> root = new NodeImpl<>();
        Node<Integer> child = new NodeImpl(root);
        child.addChild(child);

        Node<Boolean> tallChild = new NodeImpl(root);

        assertThat(root).contains(child);

        for (Node node : root){
            System.out.println("node: " + node);
        }

    }

    @Test
    public void shouldNotParentBeChildsChild(){
        Node<String> parent = new NodeImpl<>();
        Node<String> child = new NodeImpl<>(parent);

        try {
            child.addChild(parent);
        } catch (ParentAsChildException e) {
            assertThat(e).isInstanceOf(ParentAsChildException.class);
            return;
        }

        fail("No exception, but should be!");
    }


    @Test
    public void shouldDataNotBeNull(){
        Node<Object> parent = new NodeImpl<>();
        parent.setData(new Object());

        assertThat(parent.getData()).isNotNull();
    }

    
}
