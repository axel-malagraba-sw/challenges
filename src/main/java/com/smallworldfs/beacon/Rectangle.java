package com.smallworldfs.beacon;

import java.util.stream.Stream;

public record Rectangle(Point bottomLeft, Point topRight) {

    public boolean isDivisible() {
        return topRight.x() - bottomLeft.x() > 1
                || topRight.y() - bottomLeft.y() > 1;
    }

    public Stream<Rectangle> divide() {
        if (!isDivisible()) {
            return Stream.empty();
        }
        Point center = calculateCenter();

        return Stream.of(
                new Rectangle(bottomLeft, center),
                new Rectangle(center, topRight),
                new Rectangle(new Point(bottomLeft.x(), center.y()), new Point(center.x(), topRight.y())),
                new Rectangle(new Point(center.x(), bottomLeft.y()), new Point(topRight.x(), center.y())))
                .filter(Rectangle::isRectangle);
    }

    private Point calculateCenter() {
        int height = topRight.y() - bottomLeft.y();
        int length = topRight.x() - bottomLeft.x();

        return new Point(bottomLeft.x() + split(length), bottomLeft.y() + split(height));
    }

    private boolean isRectangle() {
        return bottomLeft.x() != topRight.x() && bottomLeft.y() != topRight.y();
    }

    private int split(int value) {
        return value > 1 ? (value / 2) : 0;
    }

    public Stream<Point> vertices() {
        return Stream.of(
                bottomLeft,
                topRight,
                new Point(topRight.x(), bottomLeft.y()),
                new Point(bottomLeft.x(), topRight.y()));
    }

    public int minX() {
        return bottomLeft().x();
    }

    public int minY() {
        return bottomLeft().y();
    }

    public int maxX() {
        return topRight().x();
    }

    public int maxY() {
        return topRight().y();
    }
}
