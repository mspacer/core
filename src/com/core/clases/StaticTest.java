package com.core.clases;

import com.bean.Base;
import com.bean.Department;
import com.core.oop.Child1;

public class StaticTest {
    public static final StaticTest instance = new StaticTest();
    public static final int A = init();//5;
    public static int B = 7;
    private int x;

    public StaticTest() {
        System.out.printf("StaticTest A = %d\n", A);
        this.x = A + B;
    }

    public static int init() {
        System.out.printf("init A = %d\n", A);
        return 6;
    }

    public static void main(String[] args) {
        System.out.println(StaticTest.instance.x);

        new Department(71);
        new Department(17);

        Child1 child1 = new Child1();
        callClassMethod(child1);

        System.out.println("Child1 call");
        child1.somePrMethod();
        Child1.nameClass();
        Child1.nameClass("Child1 ");
    }

    static void callClassMethod(Base base) {
        base.nameClass();
        base.nameClass("overload");
        base.finalMethod();
        base.someM();
    }



    /*
• Статические методы являются методами класса, не привязаны ни к какому объекту и не содержат указателя this на конкретный экземпляр, вызвавший метод.
• Статические методы реализуют парадигму «раннего связывания», жестко определяющую версию метода на этапе компиляции.
• По причине недоступности указателя this статические поля и методы не могут обращаться к нестатическим полям и методам напрямую, так как они
    не «знают», к какому объекту относятся, да и сам экземпляр класса может быть не создан.
• Для обращения к статическим полям и методам достаточно имени класса, в котором они определены.
• Переопределение статических методов невозможно, так как обращение к статическому методу осуществляется посредством имени класса, которо-
    му они принадлежат.
• Статические методы используются при необходимости придать функцио-нальности метода признак «окончательности», «неизменности» реализации алгоритма для данного класса
    static final - допустимо
• Статические методы могут перегружаться нестатическими, и наоборот, безограничений.
• Статические методы наследуются. Доступ возможен через имя класса-потомка
• static не может использоваться в объявление класса
     */
}
