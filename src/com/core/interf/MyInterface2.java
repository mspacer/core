package com.core.interf;

public interface MyInterface2 {
    int ID = 7;
    /*final*/ String name();

    // With Java 8
    default String defName() {
        return "default name";
    }

    default String firstName() {
        return "firstName";
    }

    static int foo() {
        return 0;
    }

    //With Java 9
/*
    private int foo1() {
        return 1;
    }

    private static int foo2() {
        return 1;
    }
*/

}
