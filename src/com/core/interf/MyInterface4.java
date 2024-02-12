package com.core.interf;

public interface MyInterface4 extends MyInterface, MyInterface2 {

    @Override
    default String defName() {
        return MyInterface.super.defName();
    }

    @Override
    default String name() {
        return MyInterface.super.name();
    }
}
