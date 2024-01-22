package com.core.annot.bean;

import com.core.annot.an.Test;

public class Sample1 {

    @Test
    public void m1() {
        System.out.println("invoke m1");
    } // Test should pass

    public void m2() {
    }

    @Test
    public void m3() { // Test should fail
        System.out.println("invoke m3 with error");
        throw new RuntimeException("Boom");
    }

    public void m4() {
    }

    public void m6() {
    }

    @Test
    public static void m7() { // Test should fail
        System.out.println("invoke m7 with error");
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }

    @Test
    public void m5() {
        System.out.println("invoke m5");

    } // INVALID USE: nonstatic method

}
