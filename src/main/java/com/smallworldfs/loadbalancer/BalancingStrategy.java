package com.smallworldfs.loadbalancer;

import java.util.Map;

public interface BalancingStrategy {

    Node selectNode(Map<String, Node> nodes);
}
