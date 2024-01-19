package com.core.types;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

public class TestPrimitive {
    public static void main(String[] args) {
        byte b1 = 11;
        byte b2 = 13;
        //byte b3 = b1 * b2;
        for (int i = 0; i < 10; i++) {
            b2 = (byte)(b2 + 10*i);
            System.out.println(b2);
        }
        final int VAL1 = 10;
        // b1 = b1 + VAL1; - int
        b1 += 1000;
        b1++;
        System.out.println(String.format("b1 = %d", b1));

        System.out.println("2%4 = " + 1%3);
        System.out.println("2%4 = " + 2%3);
        System.out.println("2%4 = " + 3%3);
        System.out.println("7%4 = " +7%4);

        int x = 0;
        int y = 0;
        System.out.println( x++ == y);
        System.out.println( x++ == y++);
        System.out.println( x++ == y++ || x++ != y++);

        int value1 = Character.digit(55, 10);
        System.out.println("value1 = " + value1);

        float res = 0.4f - 0.3f;
        System.out.println("res = " + res);
        BigDecimal big1 = new BigDecimal("0.4");
        BigDecimal big2 = new BigDecimal("0.3");
        BigDecimal bigRes = big1.subtract(big2, MathContext.DECIMAL32);
        System.out.println("bigRes = " + bigRes.toString());


        int j = -3;
        OUT: while(true) {
            for(;;) {
                while (j < 10) {
                    if (j == 0) {
                        break OUT; // прервывание while
                    } else {
                        j++;
                        System.out.printf("%d ", j);
                    }
                }
            }
        }
        System.out.println("end");

        char[] array = {'a', 'b', 'c'};
        for (char element : array) {
            element += element;
            //System.out.println(element);
        }
        System.out.println(Arrays.toString(array));

        String s1 = new String("Java12");
        String s2 = new String("12");
        String s3 = new String();

        System.out.printf("s1 + s2 = %s\n", s1 + s2);
        //System.out.printf("s1 - s2 = %s\n", s1 - s2);


    }
}
