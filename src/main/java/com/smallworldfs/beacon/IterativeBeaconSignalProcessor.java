package com.smallworldfs.beacon;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class IterativeBeaconSignalProcessor implements BeaconSignalProcessor {

    @Override
    public long countNodesAffectedByBeacon(int x1, int y1, int x2, int y2, int xl, int yl, int radius) {
        Rectangle network = new Rectangle(new Point(x1, y1), new Point(x2, y2));
        Circle beacon = new Circle(new Point(xl, yl), radius);

        return countNodesAffectedByBeacon(network, beacon);
    }

    private int countNodesAffectedByBeacon(Rectangle network, Circle beacon) {
        Set<Point> affectedNodes = new HashSet<>();
        Stack<Rectangle> subnetworks = new Stack<>();
        subnetworks.push(network);

        do {
            processSubnetwork(subnetworks, beacon, affectedNodes);
        } while (!subnetworks.isEmpty());

        return affectedNodes.size();
    }

    private void processSubnetwork(Stack<Rectangle> subnetworks, Circle beacon, Set<Point> affectedNodes) {
        Rectangle subnetwork = subnetworks.pop();

        if (subnetwork.isDivisible()) {
            subnetwork.divide()
                    .filter(beacon::collidesWith)
                    .forEach(subnetworks::push);
            return;
        }
        subnetwork.vertices()
                .filter(beacon::contains)
                .forEach(affectedNodes::add);
    }
}
