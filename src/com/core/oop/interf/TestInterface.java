package com.core.oop.interf;

public class TestInterface {
    public static void main(String[] args) {
        MyClass testInter = new MyClass();

        MyInterface.MyEnum innerEnum = testInter.getInnerEnum();
        System.out.println("innerEnum: " + innerEnum.name());

        System.out.println(new MyClass3().firstName());
    }
}
