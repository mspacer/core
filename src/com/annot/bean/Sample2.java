package com.annot.bean;

import com.annot.an.ExceptionTest;
import com.annot.an.ExceptionTestContainer;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public void m1() { // Test should pass
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public void m2() { // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public void m3() {
    } // Should fail (no exception)

    // Code containing an annotation with an array parameter
    @ExceptionTestContainer({
        @ExceptionTest(IndexOutOfBoundsException.class),
        @ExceptionTest(NullPointerException.class)
    })
    public static void doublyBad() {
        List<String> list = new ArrayList<String>();
        // The spec permits this method to throw either
        // IndexOutOfBoundsException or NullPointerException
        list.addAll(5, null);
    }
}
