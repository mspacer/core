package com.core.functional;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestPredicate {
    public static void main(String[] args) {
        Predicate<String> predicateStr1 = s -> {
            System.out.println("first");
            return s.length() < 2;
        };

        Predicate<String> predicateStr2 = predicateStr1.and(s -> {
            System.out.println("second");
            return s.length() < 2;
        }).and(s -> {
            System.out.println("third");
            return s.length() < 2;
        })/*.test("der")*/;

        //predicateStr1.test("der");
        System.out.println("---");
        predicateStr2.test("d");
        System.out.println("---");
        //predicateStr2.test("d");

        String[] arrayStr = {"as", "a", "the", " ", "d", "on", "and", ""};
        System.out.println(Arrays.stream(arrayStr).filter(s -> s.length() > 2).collect(Collectors.toList()));
    }
}
