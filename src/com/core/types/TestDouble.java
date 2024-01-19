package com.core.types;

import java.math.BigDecimal;
import java.math.MathContext;

public class TestDouble {
    public static void main(String[] args) {
        System.out.println(Double.toHexString(10));
        System.out.println(Double.valueOf(Double.toHexString(10)));
        System.out.printf("%f\n", Double.valueOf(0x7ff8000000000000L).doubleValue());
        System.out.printf("%f\n", Double.valueOf(0.123e+3).doubleValue());

        System.out.printf("bigDecimal = %.32f\n", new BigDecimal("2342342.423423423423", MathContext.DECIMAL128));
        System.out.printf("bigDecimal = %s\n", new BigDecimal("2342342.423423423423", MathContext.DECIMAL128).toString());

        char[] arrDouble = {'1', '4', '3', '6', '7', '8', '3'};
        BigDecimal bigDecimal1 = new BigDecimal(arrDouble, 2, 4, MathContext.DECIMAL32);
        System.out.printf("bigDecimal1 = %f\n", bigDecimal1.doubleValue());

        Double[] arrOfDouble = new Double[10];
        Object arrOfDouble1 = new Double[]{Double.valueOf(12.36), Double.valueOf(453.98) };
        System.out.printf("arrOfDouble1[0] = %s\n", ((Double[])arrOfDouble1)[0].toString());

        System.out.println(Double.doubleToLongBits(312312.444546131));

        System.out.printf("bigDecimal = %.32f\n", new BigDecimal(2342342.423423423423, MathContext.DECIMAL128));

    }
}
