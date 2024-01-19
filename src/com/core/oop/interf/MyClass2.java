package com.core.oop.interf;

public class MyClass2 implements MyInterface2{
    @Override
    public String name() {
        return "Hello from " + this.getClass().getName();
    }

    public void myDefName() {
        System.out.println(defName());

        //foo();
        MyInterface2.foo();
        //MyInterface2.foo2();
    }
}
