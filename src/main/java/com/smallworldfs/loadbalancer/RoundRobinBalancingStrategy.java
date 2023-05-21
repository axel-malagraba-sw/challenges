package com.smallworldfs.loadbalancer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinBalancingStrategy implements BalancingStrategy {

    private final AtomicInteger requests = new AtomicInteger();

    @Override
    public Node selectNode(Map<String, Node> nodes) {
        Node[] nodeArray = nodes.values().toArray(new Node[0]);

        if (nodeArray.length == 0) {
            throw new IllegalStateException("Load balancer has no registered nodes.");
        }
        return nodeArray[requests.getAndIncrement() % nodeArray.length];
    }
}
