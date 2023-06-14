package com.smallworldfs.beacon;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import lombok.RequiredArgsConstructor;

public class Result {

    public static long countNodesInBeacon(int x1, int y1, int x2, int y2, int xl, int yl, int radius) {
        Rectangle network = new Rectangle(new Point(x1, y1), new Point(x2, y2));
        Circle beacon = new Circle(new Point(xl, yl), radius);

        return new BeaconSignalProcessor(network, beacon).countAffectedNodes();
    }

    @RequiredArgsConstructor
    private static class BeaconSignalProcessor {

        private final Rectangle network;
        private final Circle beacon;
        private final Set<Point> affectedNodes = new HashSet<>();

        public long countAffectedNodes() {
            Stack<Rectangle> subnetworks = new Stack<>();
            subnetworks.push(network);

            do {
                processSubnetwork(subnetworks.pop(), subnetworks);
            } while (!subnetworks.isEmpty());

            return affectedNodes.size();
        }

        private void processSubnetwork(Rectangle subnetwork, Stack<Rectangle> subnetworks) {
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
}
