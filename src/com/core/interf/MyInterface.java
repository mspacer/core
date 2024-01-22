package com.core.interf;

public interface MyInterface {
    int YEAR = 2023;
    int myBirthday();

    <A,B extends MyInterface2> A testParam(A a, B b);

    static void staticMethod(){}

    interface MyInnerInterface {
        String comment() /*{
            return "Hi";
        }*/    ;
    }

    enum MyEnum {
        ONE, TWO;
    }
}
