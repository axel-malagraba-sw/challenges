package com.smallworldfs.beacon;

import static java.lang.Math.max;

public record Circle(Point center, int radius) {

    public boolean collidesWith(Rectangle rectangle) {
        return contains(
                max(max(rectangle.minX() - center.x(), center.x() - rectangle.maxX()), 0),
                max(max(rectangle.minY() - center.y(), center.y() - rectangle.maxY()), 0));
    }

    public boolean contains(Point point) {
        return contains(point.x() - center.x(), point.y() - center.y());
    }

    private boolean contains(int distanceX, int distanceY) {
        return square(distanceX) + square(distanceY) <= square(radius);
    }

    private long square(int value) {
        return (long) value * value;
    }
}
