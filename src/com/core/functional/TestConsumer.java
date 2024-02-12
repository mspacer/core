package com.core.functional;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestConsumer {
    public static void main(String[] args) {
        Consumer<MyConsumer> consumer = myConsumer -> myConsumer.setTitle(myConsumer.getTitle() + " changed");
        MyConsumer myConsumer = new MyConsumer("first title");
        consumer.accept(myConsumer);
        System.out.println(myConsumer.getTitle());

        BiConsumer<MyConsumer, String> biConsumer = (myConsumer1, s) -> myConsumer.setTitle(myConsumer.getTitle() + " - " + s);
        biConsumer.andThen((myConsumer1, s) -> myConsumer.setTitle(myConsumer.getTitle() + " and then - " + s))
                .accept(myConsumer, "biConsumer");
        System.out.println(myConsumer.getTitle());

    }
}

class MyConsumer {
    private String title;

    public MyConsumer(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
