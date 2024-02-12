package com.core.functional.custom;

public class TestMyFunction {
    public static void main(String[] args) {
        MyFunction<SomeFnClass, Integer> function = new MyFunction<SomeFnClass, Integer>() {
            @Override
            public Integer apply(SomeFnClass someFnClass) {
                System.out.println("apply init " + someFnClass.getInner().length());
                return someFnClass.getInner().length();
            }
        };

//  Выход одного метода является входом для другого
//      compose.apply выполняется перед function.apply, поэтому:
//  1) в compose передается класс с apply, реализующим before function.apply.
//      Эта функция на выходе должна вернуть SomeFnClass для function.apply
//      Поскольку function определена как <SomeFnClass, Integer> , т.е. вход ее apply - SomeFnClass,
//      поэтому аргумент compose оперирует типами <SomeFnClass, SomeFnClass>
//  2) compose возвращает объект MyFunction с переопределенным apply, которая должна вызвать before аргумента,
//      затем apply своего внешнего класса. Для первого вызова compose это function.apply.
//      apply своего внешнего класса имеет аргумент SomeFnClass, и должна вернуть SomeFnClass, поскольку это значене
//      перередается следующему compose.apply. Поэтому в объекте, возвращаемом compose функция apply принимает и
//      возавращает SomeFnClass и он будет внешнии классом для следующего объекта из compose или andThen
//
//  Функция andThen.apply выполняется после function.apply, поэтому:
//  1) ей на вход передается класс с apply, реализующим after function.apply.
//      Эта функция получачает результат работы function.apply, т.е. Integer, а результатом может быть любой объект,
//      который буде "входом" следующему andThen


        System.out.println(
        function.compose(new MyFunction<SomeFnClass, SomeFnClass>() {
            @Override
            public SomeFnClass apply(SomeFnClass someFnClass) {
                System.out.println("compose1 ");
                return someFnClass.of(someFnClass.getInner());
            }
        }).compose(new MyFunction<SomeFnClass, SomeFnClass>() {
            @Override
            public SomeFnClass apply(SomeFnClass someFnClass) {
                System.out.println("compose2 ");
                return someFnClass.of(someFnClass.getInner());
            }
        }).andThen(new MyFunction<Integer, String>() {
            @Override
            public String apply(Integer length) {
                System.out.println("andThen1 " + length);
                return "" + length + 3;
            }
        }).andThen(new MyFunction<String, String>() {
            @Override
            public String apply(String length) {
                System.out.println("andThen2 " + length);
                return "Finish";
            }
        }).apply(new SomeFnClass("init "))
        );
    }
}
