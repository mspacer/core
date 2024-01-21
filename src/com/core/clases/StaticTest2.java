package com.core.clases;

/**
 - Когда класс загружается в память, для него сразу создается статический объект класса.
    Этот объект хранит статические переменные класса (статические поля класса). Статический объект класса существует,
    даже если не был создан ни один обычный объект класса.
 */
class SubClass extends StaticTest2 {
    public static /*final*/ int NUM = 0;
}

public class StaticTest2 {
    public static /*final*/ int NUM = 1;

    public static void main(String[] args) {
        StaticTest2 base = new StaticTest2();
        SubClass subClass = new SubClass();
        System.out.print(StaticTest2.NUM);
        System.out.print(SubClass.NUM);
        System.out.print(base.NUM);

        StaticTest2 base1 = (StaticTest2) subClass;
        base1 = null;
        // Переменная типа.NullPointerException, если NUM не static.
        // (На этапе компиляции замещаестя адресом статической переменной TODO(уточнить) )
        System.out.println(base1.NUM);
        //System.out.println(base1.getClass().getTypeName());
    }
}
