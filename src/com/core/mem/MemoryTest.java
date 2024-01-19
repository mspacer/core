package com.core.mem;

import java.util.ArrayList;
import java.util.List;

public class MemoryTest {
    public static void main(String[] args) {
        List<String> testStr = new ArrayList<String>();
        testStr.add("First string");
        System.out.println("testStr.hash = " + testStr.hashCode());
        System.out.println("First string.hash = " + testStr.get(0).hashCode());

        infoAboutList(testStr);
        System.out.println("");
        changeList(testStr);
        System.out.println("");

        testStr.add("Second string");
        infoAboutList(testStr);

        createDoubleArray();
        System.out.println("");
    }

    public static void infoAboutList(List<String> localStr) {
        System.out.println("localStr.size = " + localStr.size());
        System.out.println("localStr.hash = " + localStr.hashCode());
        System.out.println("First string.hash = " + localStr.get(0).hashCode());
    }

    public static void changeList(List<String> localStr) {
        localStr = new ArrayList<String>();
        localStr.add("First string");
        localStr.add("New String");
        infoAboutList(localStr);
    }

    public static void createDoubleArray() {
        double[] doubles = new double[100000];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = 3.14 * i;
        }
        System.out.println("");
    }
}