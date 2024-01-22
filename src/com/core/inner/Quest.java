package com.core.inner;

public class Quest {
    public static void main(String[] args) {
        new Owner().new Inner().method();

        System.out.println(Owner.Inner.i);
        System.out.println(new Owner().new Inner().i);
    }
}

class Clazz {
    static int i = 1;

    {System.out.print("clazz ");}
    public void method() {
        System.out.print("method1 ");
    }
}

class Owner{
    {System.out.print("owner ");}
    private int N=10;
    class Inner extends Clazz {
        {System.out.print("inner ");}
        public void method(){
            System.out.print("method2 " + i);
        }
    }
}