package com.core.interf;

/**
 - public либо пакетный - [public] class NameClass implements Name1, Name2,…, NameN {}
 - Все объявленные в интерфейсе абстрактные методы автоматически трактуются как public abstract,
    а все поля — как public static final, даже если они так не объявлены
 - Интерфейсы могут объявлять статические методы и перечисления
 - В интерфейсах могут объявляться методы с реализацией с ключевым словом default. Эти методы могут быть public или private
 - Если по каким-либо причинам для данного класса не предусмотрена реализация метода или его реализация нежелательна,
    рекомендуется генерация непроверяемого исключения в теле метода, а именно:
        public boolean blocking() {
            throw new UnsupportedOperationException();
        }
 -

 */
public  class TestInterface {
    public static void main(String[] args) {
        MyClass testInter = new MyClass();

        MyInterface.MyEnum innerEnum = testInter.getInnerEnum();
        System.out.println("innerEnum: " + innerEnum.name());

        System.out.println(new MyClass3().firstName());
    }
}
