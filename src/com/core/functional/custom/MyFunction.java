package com.core.functional.custom;

public abstract class MyFunction<T, R> {
    private final MyFunction<T, R> current = this;

    public abstract R apply(T t);

    public  <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        return new MyFunction<V, R>() {
            @Override
            public R apply(V v) {
                return current.apply(before.apply(v));
            }
        };
    }

    public  <V> MyFunction<T, V> andThen(MyFunction<? super R, /*? extends*/ V> after) {
        return new MyFunction<T, V>() {
            @Override
            public V apply(T t) {
                return after.apply(current.apply(t));
            }
        };
    }
}