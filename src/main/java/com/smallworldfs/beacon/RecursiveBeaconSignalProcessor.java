package com.smallworldfs.beacon;

import java.util.HashSet;
import java.util.Set;

public class RecursiveBeaconSignalProcessor implements BeaconSignalProcessor {

    @Override
    public long countNodesAffectedByBeacon(int x1, int y1, int x2, int y2, int xl, int yl, int radius) {
        Rectangle network = new Rectangle(new Point(x1, y1), new Point(x2, y2));
        Circle beacon = new Circle(new Point(xl, yl), radius);
        Set<Point> affectedNodes = new HashSet<>();

        processNetwork(network, beacon, affectedNodes);

        return affectedNodes.size();
    }

    private void processNetwork(Rectangle network, Circle beacon, Set<Point> affectedNodes) {
        if (network.isDivisible()) {
            network.divide()
                    .filter(beacon::collidesWith)
                    .forEach(subnetwork -> processNetwork(subnetwork, beacon, affectedNodes));
            return;
        }
        network.vertices().filter(beacon::contains).forEach(affectedNodes::add);
    }
}
