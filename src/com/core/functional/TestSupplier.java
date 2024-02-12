package com.core.functional;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static java.lang.System.out;

public class TestSupplier {
    public static void main(String[] args) {
        Supplier<MySupplier> supplier = () -> new MySupplier("text");
        MySupplier mySupplier = supplier.get();
        out.println(mySupplier);

        out.println(Arrays.asList(randomArray(10, 30)));
    }

    public static Supplier<Integer[]> buildArray(int size) {
        final int length = size > 0 ? size : 1;
        return () -> new Integer[length];
    }

    public static Integer[] randomArray(int size, int randomValue) {
        Integer[] array = buildArray(size).get();
        //Supplier<Integer> numberSupplier = () -> new Random().nextInt(randomValue);
        IntSupplier numberSupplier = () -> new Random().nextInt(randomValue);

        for (int i = 0; i < array.length; i++) {
            array[i] = numberSupplier.getAsInt();//get();
        }
        return array;
    }
}

class MySupplier {
    private String title;

    public MySupplier(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MySupplier{" +
                "title='" + title + '\'' +
                '}';
    }
}
