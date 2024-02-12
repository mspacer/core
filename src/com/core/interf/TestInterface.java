package com.core.interf;

/**
 - Реализация интерфейса. Класс public либо пакетный - [public] class NameClass implements Name1, Name2,…, NameN {}
    Если требуется еще наследование от класса, то сперва extends ClassName затем implements Name1,....
 - Все объявленные в интерфейсе абстрактные методы автоматически трактуются как public abstract,
    а все поля — как public static final, даже если они так не объявлены
 - Интерфейсы могут объявлять статические методы и перечисления
 - В интерфейсах могут объявляться методы с реализацией с ключевым словом default.
    Эти методы могут быть public или private (java 9 без слова default)
    их можно переопределять в дочерних интерфейсах и классах
 - Есле одинаковый default метод объявлен в двух интерфейсах, требуется его переопределение в потомках (как классы, так и интерфейсы).
    Аналогично, если один дефолтный, а другой абстрактный
 - Если объявлены две одинаковые переменные в двух интерфейсах, в классе-наследнике требуется задавать имя интерфейса для доступа к ней.
 - Если по каким-либо причинам для данного класса не предусмотрена реализация метода или его реализация нежелательна,
    рекомендуется генерация непроверяемого исключения в теле метода, а именно:
        public boolean blocking() {
            throw new UnsupportedOperationException();
        }
 - Фундаментальная разница между интерфейсом и абстрактным классом заключается в том, что интерфейс определяет только поведение.
   Он не сообщает ничего про объект, который будет его реализовывать
 - Появление методов по умолчанию в интерфейсах разрешило множественное наследование поведения. В классическом варианет интерфейсов (до java8),
    их наследники должны были сами реализовывать методы, тем самым определяя собственные варианты поведения.
    Появление приватных методов вносит еще и наследуемую (причем скрытую от наследников) бизнесс-логику в поведение интерфейсов

 */
public  class TestInterface {
    public static void main(String[] args) {
        MyClass testInter = new MyClass();

        MyInterface.MyEnum innerEnum = testInter.getInnerEnum();
        System.out.println("innerEnum: " + innerEnum.name());

        System.out.println(new MyClass3().firstName());

        First a = (First)new Clazz();
        //Second b = (First)new Klass();
        First c = (First)new Klass();
        // Объявление корректное, но ведет к java.lang.ClassCastException
        // работает только с интерфейсами
        Second d = (Second) new Clazz();
        //Klass e = (Second)new Clazz();
    }
}

interface First {}
interface Second extends First {}
class Klass implements Second{}
class Clazz implements First {}