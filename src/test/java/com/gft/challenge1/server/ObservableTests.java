package com.gft.challenge1.server;

import com.gft.challenge1.server.node.NodeImpl;
import com.gft.challenge1.server.node.SuperAlgorithm;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ObservableTests {

    @Test
    public void ObservableTests() {

        val root = new NodeImpl<>();
        val child = new NodeImpl<>(root, "ABC");
        val expected = SuperAlgorithm.convert2Iterable(root);

        val actual = Observable
                .fromIterable(expected).blockingIterable();

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
