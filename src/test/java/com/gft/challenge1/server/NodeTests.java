package com.gft.challenge1.server;
import com.gft.challenge1.server.model.Node;
import lombok.NonNull;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class NodeTests {

    @Test
    public void shouldBeRootNode(){
        Node root = new Node(null);
        assertThat(root.isRoot()).isEqualTo(true);

        Node root2 = new Node();

        assertThat(root2.isRoot()).isEqualTo(true);
    }

    @Test
    public void shouldNotBeRootNode(){
        Node root = new Node();
        Node node = new Node(root);

        assertThat(node.isRoot()).isEqualTo(false);
    }

    @Test
    public void shouldNotHaveChildren(){
        Node root = new Node();
        Node node = new Node(root);

        assertThat(node.hasChildren()).isEqualTo(false);
    }

    @Test
    public void shouldHaveChildren(){
        Node root = new Node();
        Node node = new Node(root);

        assertThat(root.hasChildren()).isEqualTo(true);
    }

    @Test
    public void shoulRemoveChildFromChildren(){
        Node root = new Node();
        Node node = new Node(root);
        root.removeChild(node);

        assertThat(root.hasChildren()).isEqualTo(false);
    }

    @Test
    public void shoulBeRootAfterRemoveFromRootsChildren(){
        Node root = new Node();
        Node node = new Node(root);
        root.removeChild(node);

        assertThat(node.isRoot()).isEqualTo(true);
    }

    @Test
    public void shouldNotBeARootAfterAddToAnotherRoot(){
        Node root = new Node();
        Node anotherRoot = new Node();
        anotherRoot.addChild(root);

        assertThat(root.isRoot()).isEqualTo(false);
    }

    @Test
    public void shouldBeParentOfChild(){
        Node root = new Node();
        Node child = new Node(root);

        assertThat(child.getParent().equals(root)).isEqualTo(true);
    }

    @Test
    public void shouldBeParentOfChild(){
        Node root = new Node();
        Node child = new Node(root);

        assertThat(child.getParent().equals(root)).isEqualTo(true);
    }





}
