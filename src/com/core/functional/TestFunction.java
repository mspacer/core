package com.core.functional;

import com.core.functional.custom.SomeFnClass;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class TestFunction {
    public static void main(String[] args) {
        function();
        biFunction();

    }

    private static void biFunction() {
        System.out.println("--biFunction--");
        BiFunction<Double, Integer, String> biFunction = (aDouble, integer) -> "apply " + aDouble.toString() + "/" + integer.toString();
        System.out.println(biFunction.andThen(s -> s + " andThen")
                .apply(5.343, 77));

        // расшширяет biFunction с одинаковыми типами
        BinaryOperator<String> binaryOperator = (s, s2) -> "BinaryOperator " + s + "/" + s2;
        System.out.println(binaryOperator.apply("Str1", "Str2"));

        System.out.println(BinaryOperator.maxBy((o1, o2) -> o1.toString().length() - o2.toString().length())
                .apply("Str1", "Str05"));
        System.out.println(BinaryOperator.maxBy(Comparator.comparingInt(o -> o.toString().length()))
                .apply("Str1", "Str06"));


        System.out.println("----------");
    }

    private static void function() {
        System.out.println("--function--");
        Function<SomeFnClass, Integer> function = (s) -> {
            System.out.println("length of " + s.getClass());
            System.out.println("length of " + s.getInner());
            return s.getInner().length();
        };
        // см коммент в TestMyFunction
        function.compose((SomeFnClass o) -> {
            System.out.println("compose1 of " + o.getClass());
            return o.of("compose1");
        }).compose((SomeFnClass o) -> {
            System.out.println("compose2 of " + o);
            return o.of("compose2");
        }).andThen(integer -> {
            System.out.println("andThen: " + integer);
            return /*integer*/new SomeFnClass("init ");
        }).compose((SomeFnClass o) -> {
            System.out.println("compose3 of " + o);
            return o.of("compose3");
        }).apply(new SomeFnClass("init "));
        System.out.println("----------");
    }
}