package com.core.clases;

public class EnumTest {
    public static void main(String[] args) {
        Shape sh = Shape.CIRCLE;
        System.out.println(sh.name());
        sh = Shape.valueOf("TRIANGLE");
        sh = Shape.valueOf( Shape.class,"TRIANGLE");
        System.out.println(sh.name());
        System.out.println(sh.ordinal());
    }
}
