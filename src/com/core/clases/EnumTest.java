package com.core.clases;

import com.bean.ShapeEnum;

/**
 - перечисление - public final class, наследющий abstract class Enum
   объявленные члены - public static final экземпляры типа
 - перечесление мозжет реализовывать интерфейсы, но не расширять классы, т.к. уже является наследником
 - коструктор мозжет быть только пакетным или приватным.
 - не можно создавать экземпляры Enum вне границ Enum, поскольку у Enum нет public конструктора
 - можно создать пустой enum без экземпляров со статическими методами - аналог утилетарного класса
 - emum реализовывает Comparable (можно использовать энумы в TreeSet или TreeMap),
    поэтому сравнивать их нужно через compareTo. Используется поле order, хранящее порядковый номер при объявлении
 - Поскольку экземпляры Enum компилируются в константу, можено внедрять их внутрь case и switch
 - легко реализовать Singleton pattern
    public enum EasySingleton{
        INSTANCE;
    }
 - enum может содержать абстратный метод. В этом случае перечисление реализуется с помощью анонимного внутреннего класса (EnumAsAnonimous).
 */
public class EnumTest {
    public static void main(String[] args) {
        ShapeEnum sh = ShapeEnum.CIRCLE;
        System.out.println(sh.name());
        sh = ShapeEnum.valueOf("TRIANGLE");
        sh = ShapeEnum.valueOf( ShapeEnum.class,"TRIANGLE");

        sh = ShapeEnum.valueOf("TRIANGLE");
        sh = ShapeEnum.valueOf( ShapeEnum.class,"TRIANGLE");
        System.out.println(sh.name());
        System.out.println(sh.ordinal());

        ShapeEnum sh1 = ShapeEnum.CIRCLE;
        ShapeEnum sh2 = ShapeEnum.CIRCLE;
        System.out.println(sh1 == sh2);
        System.out.println(sh1.compareTo(sh2));

        EmptyEnum.comment();
        //IllegalArgumentException: No enum constant com.core.clases.EmptyEnum.?
        //EmptyEnum e = EmptyEnum.valueOf("?");

    }
}
