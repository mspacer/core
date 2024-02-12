package com.core.interf;

public interface MyInterface {
    int ID = 1;
    int YEAR = 2023;
    int myBirthday();

    <A,B extends MyInterface2> A testParam(A a, B b);

    static void staticMethod(){}

    default String defName() {
        return "MyInterface default name";
    }

    default String name() { return ""; }

    interface MyInnerInterface {
        String comment() /*{
            return "Hi";
        }*/    ;
    }

    enum MyEnum {
        ONE, TWO;
    }
}
