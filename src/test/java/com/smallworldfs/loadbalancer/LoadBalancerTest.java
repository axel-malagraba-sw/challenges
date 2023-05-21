package com.smallworldfs.loadbalancer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LoadBalancerTest {

    private final LoadBalancer loadBalancer = new LoadBalancer();

    @Nested
    class GetNode {

        @Test
        void throws_exception_when_no_nodes_are_registered() {
            IllegalStateException exception = assertThrows(IllegalStateException.class, loadBalancer::getNode);

            assertThat(exception).hasMessage("Load balancer has no registered nodes.");
        }

        @Test
        void returns_node_when_only_one_registered_node() {
            Node node = registerNode("10.10.10.10");

            assertThatNextNodeIs(node);
            assertThatNextNodeIs(node);
        }

        @Test
        void returns_a_different_node_everytime_using_default_strategy() {
            Node node1 = registerNode("10.10.10.10");
            Node node2 = registerNode("10.10.10.11");

            assertThatNextNodeIs(node1);
            assertThatNextNodeIs(node2);
            assertThatNextNodeIs(node1);
            assertThatNextNodeIs(node2);
        }
    }

    @Nested
    class RegisterNode {

        @Test
        void adds_new_node() {
            assertThat(loadBalancer.getNodes()).isEmpty();

            Node node = registerNode("10.111.66.10");

            assertThat(loadBalancer.getNodes()).contains(node);
        }
    }

    private void assertThatNextNodeIs(Node node) {
        assertThat(loadBalancer.getNode()).isEqualTo(node);
    }

    private Node registerNode(String ip) {
        Node node = new Node(ip);

        loadBalancer.registerNode(node);

        return node;
    }
}
