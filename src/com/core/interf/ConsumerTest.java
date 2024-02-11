package com.core.interf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        String str = "as a- the-d -on and";
        String regex = "-";
        String regex2 = "\\s";
        Consumer<String> cons = s -> System.out.println(Arrays.asList(s.split(regex)));
        //cons.accept(str);
        //cons.andThen(s -> System.out.println(Arrays.asList(s.split(regex2))))
        //        .accept(str);

        final List<String> split = new ArrayList<>();
        Consumer<String> cons2 = s -> split.addAll(Arrays.asList(s.split(regex)));
        cons2.andThen(s -> split.addAll(Arrays.asList(s.split(regex2))))
                .accept(str);
        System.out.println(split);

        BiConsumer<String, String> bicons = (s, s2) -> System.out.println(Arrays.asList(s.split(s2)));
        bicons.accept(str, regex);
    }
}
