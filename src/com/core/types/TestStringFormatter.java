package com.core.types;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.MAY;

public class TestStringFormatter {

    public static void main(String[] args) {
        Calendar c = new GregorianCalendar(1995, MAY, 23);
        //String s = String.format("Duke's Birthday: %1$tb (%1$tm) %1$te, %1$tY", c);
        System.out.printf("Duke's Birthday: %1$tb (%1$tm) %1$te, %1$tY\n", c);

        System.out.printf("%f\n", Double.MAX_VALUE);

        String.format("fsfs s %d", 401);

    }
}
