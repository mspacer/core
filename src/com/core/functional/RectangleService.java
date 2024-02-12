package com.core.functional;

public class RectangleService implements ShapeServiceFn{
    @Override
    public double perimeter(double a, double b) {
        return 2 * (a + b);
    }
}
