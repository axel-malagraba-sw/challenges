package com.smallworldfs.beacon;

public interface BeaconSignalProcessor {

    long countNodesAffectedByBeacon(int x1, int y1, int x2, int y2, int xl, int yl, int radius);
}
