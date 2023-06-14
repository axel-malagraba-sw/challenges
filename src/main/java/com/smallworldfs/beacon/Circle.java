package com.smallworldfs.beacon;

import static java.lang.Math.max;

public record Circle(Point center, int radius) {

    public boolean contains(Point point) {
        return contains(point.x() - center.x(), point.y() - center.y());
    }

    public boolean collidesWith(Rectangle rectangle) {
        int distanceX = max(max(rectangle.minX() - center.x(), center.x() - rectangle.maxX()), 0);
        int distanceY = max(max(rectangle.minY() - center.y(), center.y() - rectangle.maxY()), 0);

        return contains(distanceX, distanceY);
    }

    public boolean contains(int distanceX, int distanceY) {
        return square(distanceX) + square(distanceY) <= square(radius);
    }

    private int square(int value) {
        return value * value;
    }
}
