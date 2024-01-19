package com.core.oop.interf;

public interface MyInterface3 extends MyInterface2 {

    @Override
    default String firstName() {
        System.out.println(defName());
        //super.firstName();
        return "interf firstName3";
    }
}
