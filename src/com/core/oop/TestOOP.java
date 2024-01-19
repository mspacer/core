package com.core.oop;

import com.bean.Base;

public class TestOOP {
    public static void main(String[] args) {

        A a1 = new A(0);
        A a2 = new A(0);
        System.out.print(a1 == a2);
        System.out.print(", " + a1.equals(a2));
        System.out.println(", " + (a1.hashCode() == a2.hashCode()));
    }

}

class A {
    int a;
    A(int a) {
        this.a = a;
    }
}
