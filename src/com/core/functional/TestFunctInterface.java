package com.core.functional;

/**
 - Функциональный интерфейс должен иметь один единственный абстрактный метод и любое число статических и default-методов
 - Для объявления такого интерфейса используется аннотация @FunctionalInterface, что предполает его использование в виде
    лямбда-выражения. Интерфейс без аннотации не запрещает их использование в лямбда-выражениях.
 - На самом деле лямбда-выражение представляет сокращенную запись анонимного класса
 - Функциональные интерфейсы можно наследовать друг от друга, но в наследнике не должно быть акстрактного метода
 - При передаче ФИ в качестве параметра функции создается впечатление, что в метод передается функция.
    На самом деле в функцию передается объект, в который «завернута» функция.
 - Все представленные функциональные интерфейсы можно разделить на четыре группы:
    Function<T,R>, Predicate<T>, Consumer<T> и Supplier<T>

 */
public class TestFunctInterface {
    public static void main(String[] args) {
        ShapeServiceFn rectangleService = (a, b) -> 2 * (a + b);
        System.out.println(rectangleService.perimeter(4, 6));

        ShapeServiceFn rectangleService2 = (a, b) -> {
           System.out.println("rectangleService2");
           return  2 * (a + b);
        };
        System.out.println(rectangleService2.perimeter(3, 4));

        fnAsParameter(2, 9, (a, b) -> (a + b) * 4 );

    }

    private static void fnAsParameter(double a, double b, ShapeServiceFn fn) {
        System.out.println("--fnAsParameter--");
        System.out.println(fn.perimeter(a, b));
        System.out.println("-----------------");
    }
}
