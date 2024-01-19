package com.bean;

public class Base {

    public void someM() {
        System.out.println("from Base");
    }

    public static void nameClass() {
        System.out.println("Base");
    }

    protected static final void nameClass2() {
        System.out.println("Base 2");
    }

    public static void nameClass(String s) {
        System.out.println("Base " + s);
    }

    public final void finalMethod() {
        System.out.println("finalMethod");
    }

    private void somePrMethod() {
        System.out.println("Base somePrMethod");
    }

}
