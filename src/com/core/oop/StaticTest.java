package com.core.oop;

import com.bean.Base;
import com.bean.Department;
import com.core.interf.MyInterface2;

/**
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
• Для статических методов принципы «позднего связывания» не работают
• static не может использоваться в объявление класса
• Поля с модификатором final static будут инициализироваться один раз при загрузке (инициализации) класса и хранится всё время (или то время пока востребован класс),
    а поля с модификатором final будут каждый раз создаваться в момент создания экземпляра
• Интерфейсы могут содержать статические методы. Для полей интерфейса static указывать не надо
• переопределяемый метод в дочернем классе не может стать статическим
• статические методы всегда лучще вызывать через имя класс, а не через ссылку на объект. Если ссылка будет null, ее использование не приведет NullPointerException
*/

public class StaticTest {
    public static /*final*/ StaticTest instance = new StaticTest();
    public static final int A = 5;
    public static final Boolean Bl = true;
    public static int B = 7;
    private int x;

    public StaticTest() {
        System.out.printf("StaticTest A = %d, B = %d\n", A, B);
        System.out.println("StaticTest Bl = " + Bl);
        this.x = A + B;
    }

    public static void main(String[] args) {
        System.out.println(StaticTest.instance.x);
        System.out.println("StaticTest Bl = " + Bl);
        //System.out.println(new StaticTest().x);
        System.out.println("_________________");

        new Department(71);
        new Department(17);

        Child1 child1 = new Child1();
        callClassMethod(child1);

        System.out.println("Child1 call");
        child1.somePrMethod();
        Child1.nameClass();
        Child1.nameClass("Child1 ");
        child1 = null;
        child1.nameClass("null link"); // will not NullPointerException !

        System.out.println(MyInterface2.ID);
    }

    static void callClassMethod(Base base) {
        base.nameClass();
        base.nameClass("overload");
        base.finalMethod();
        base.someM();
    }




}
