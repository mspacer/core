package com.core.types;

public class TestString {
    public static void main(String[] args) {
        char ch[] = {'1', '2', '3' };
        char ch2[] = {1, 2, 3 };
        char ch3[] = {49, 50, 51 };
        String s = new String(ch3);
        System.out.println(s);
        System.out.println(new String(ch2));
        System.out.println(new String(ch3));

    }
}
