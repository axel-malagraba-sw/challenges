package com.smallworldfs.loadbalancer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancer {

    private final Map<String, Node> nodes = new ConcurrentHashMap<>();
    private final BalancingStrategy strategy;

    public LoadBalancer() {
        this(new RoundRobinBalancingStrategy());
    }

    public LoadBalancer(BalancingStrategy strategy) {
        this.strategy = strategy;
    }

    public Node getNode() {
        return strategy.selectNode(nodes);
    }

    Collection<Node> getNodes() {
        return nodes.values();
    }

    public void registerNode(Node node) {
        if (!nodes.containsKey(node.ip())) {
            nodes.put(node.ip(), node);
        }
    }
}
