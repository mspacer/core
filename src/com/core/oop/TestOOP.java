package com.core.oop;


import com.core.clases.LinkPackageClass;

public class TestOOP {
    public static void main(String[] args) {
        equalObjects();

        //Not public
        //PackageClass packageClass = new PackageClass();
        //new LinkPackageClass().getPackageClass().toString();
        new LinkPackageClass().getPackageClass();

    }

    private static void equalObjects() {
        System.out.println("equalObjects");
        A a1 = new A(0);
        A a2 = new A(0);
        System.out.print(a1 == a2);
        System.out.print(", " + a1.equals(a2));
        System.out.println(", " + (a1.hashCode() == a2.hashCode()));

        A a3 = new B(5);
        System.out.println("a3.getA() = " + a3.getA());
        System.out.println("((B)a3).getSuperA() = " + ((B)a3).getSuperA());

        A a4 = (A)new B(6);
        System.out.println("a4.getA() = " + a4.getA());
        // Переменные не наследуются и, следовательно, не замещаются в дочерних классах
        // поэтому в классе B есть две переменные a
        System.out.println("a4.a = " + a4.a);

        System.out.println("________________");
    }

}

class A {
    int a;
    A() {
        System.out.print(0);
    }
    A(int a) {
        this.a = a;
    }
    int getA() {
        return a;
    }
}

class B extends A{
    int a;
    B() {
    }
    B(int a) {
        super(a * 10);
        this.a = a;
    }

    @Override
    protected int getA() {
        return a;
    }

    int getSuperA() {
        return super.a;
    }
}

