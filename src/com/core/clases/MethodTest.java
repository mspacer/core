package com.core.clases;

public class MethodTest {
    public static void main(String... args) {
        printArgCount(args);

        Integer[] i = { 1, 2, 3, 42, 5 };
        printArgCount(7, "No", true, null);
        printArgCount(i, i, i);
        printArgCount(i, 4, 42);
        printArgCount(i); // call method 1
        //printArgCount(42, 7); //TODO Непонятная ошибка
        // printArgCount(); //compile error: overload uncertainty!

        printFloat(Float.valueOf(45.25f));
        printFloat(45.25f);

        printDigitInt(1L);
        printDigitInt(1000000);

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

    public static void printDigitInt(Object l) {
        System.out.printf("Int : %d\n", l);
    }

    public static void printDigitInt(long l) {
        System.out.printf("long : %d\n", l);
    }

    public static void printDigitInt(int l) {
        System.out.printf("Int : %d\n", l);
    }


}
