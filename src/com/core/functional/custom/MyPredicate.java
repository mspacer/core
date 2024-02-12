package com.core.functional.custom;

public abstract class MyPredicate<T> {
    private final MyPredicate<T> current = this;

    public abstract boolean test(T s);

    public MyPredicate<T> and(MyPredicate<T> other) {
        return new MyPredicate<T>() {
            public boolean test(T s) {
                return current.test(s) && other.test(s);
            }
        };
    }

    public MyPredicate<T> or(MyPredicate<T> other) {
        return new MyPredicate<T>() {
            public boolean test(T s) {
                return current.test(s) || other.test(s);
            }
        };
    }
}