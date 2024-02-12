package com.core.interf;

public class MyClass4 implements MyInterface, MyInterface2 {

    public MyClass4() {
        // System.out.println(ID);
        System.out.println(YEAR);
    }

    // Необходимо переопределить, т.к. такой метод объявлен в двух родителях, несмотря, что он default
    @Override
    public String defName() {
        return MyInterface.super.defName();
    }

    @Override
    public int myBirthday() {
        return 0;
    }

    @Override
    public <A, B extends MyInterface2> A testParam(A a, B b) {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
