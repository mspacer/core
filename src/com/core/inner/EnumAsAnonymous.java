package com.core.inner;

import java.util.StringJoiner;

public enum EnumAsAnonymous {
    RECTANGLE (2, 3) {
        public double computeSquare() {
            System.out.println("override RECTANGLE");
            return this.getA() * this.getB();
        }
    },
    TRIANGLE (4, 5) {
        public double computeSquare() {
            System.out.println("override TRIANGLE");
            return this.getA() * this.getB() / 2;
        }
    };

    private double a;
    private double b;

    EnumAsAnonymous(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public abstract double computeSquare();

    @Override
    public String toString() {
        return new StringJoiner(", ", EnumAsAnonymous.class.getSimpleName() + "[", "]")
                .add("a=" + a)
                .add("b=" + b).toString();
    }
}
