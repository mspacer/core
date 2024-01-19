package com.core.oop;

import com.bean.Base;

public class Child1 extends Base {

    public void callMethods() {
        finalMethod();
    }

    @Override
    public void someM() {
        System.out.println("from Child1");
    }

    public static void nameClass() {
        Base.nameClass();
        Child1.nameClass2();
        System.out.println("Child1");
    }

/*
    public final void finalMethod() {
        System.out.println("finalMethod");
    }
*/
    public void somePrMethod() {
        System.out.println("Child1 somePrMethod");
    }

}
