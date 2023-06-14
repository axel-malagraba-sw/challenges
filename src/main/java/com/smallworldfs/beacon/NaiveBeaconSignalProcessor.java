package com.smallworldfs.beacon;

public class NaiveBeaconSignalProcessor implements BeaconSignalProcessor {

    @Override
    public long countNodesAffectedByBeacon(int x1, int y1, int x2, int y2, int xl, int yl, int radius) {
        long count = 0;
        Circle beacon = new Circle(new Point(xl, yl), radius);

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if (beacon.contains(new Point(x, y))) {
                    count++;
                }
            }
        }
        return count;
    }
}
