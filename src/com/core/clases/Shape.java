package com.core.clases;

public enum Shape {
    RECTANGLE, TRIANGLE, CIRCLE;
    public double defineSquare(double ... x) {
// here may be checking the parameters for correctness
        double area;
        switch (this) {
            case RECTANGLE:
                area = x[0] * x[1];
                break;
            case TRIANGLE:
                area = x[0] * x[1] / 2;
                break;
            case CIRCLE:
                area = Math.pow(x[0], 2) * Math.PI;
                break;
            default:
                throw new EnumConstantNotPresentException(getDeclaringClass(), name());
        }
        return area;
    }
}
