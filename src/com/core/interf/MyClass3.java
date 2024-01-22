package com.core.interf;

public class MyClass3 implements MyInterface3{
    @Override
    public String name() {
        return "Hello from " + this.getClass().getName();
    }

    @Override
    public String firstName() {
        return MyInterface3.super.firstName() + "/" +"class MyClass3";
    }
}
