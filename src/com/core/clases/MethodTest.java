package com.core.clases;

public class MethodTest {
    public static void main(String... args) {
        printArgCount(args);

        Integer[] i = { 1, 2, 3, 42, 5 };
        printArgCount(7, "No", true, null);
        printArgCount(i, i, i);
        printArgCount(i, 4, 42);
        printArgCount(i); // call method 1
        //printArgCount(42, 7);
        // printArgCount(); //compile error: overload uncertainty!

        printFloat(Float.valueOf(45.25f));
        printFloat(45.25f);
    }

    public static void printArgCount(Object... args) { // 1
        System.out.println("Object args: " + args.length);
    }

    public static void printArgCount(Integer[]...args) { // 2
        System.out.println("Integer[ ] args: " + args.length);
    }

    public static void printArgCount(int... args) { // 3
        System.out.print("int args: " + args.length);
    }

    public static void printFloat(Float f) {
        System.out.printf("Float : %f\n", f.floatValue());
    }

    public static void printFloat(float f) {
        System.out.printf("float : %f\n", f);
    }

}
