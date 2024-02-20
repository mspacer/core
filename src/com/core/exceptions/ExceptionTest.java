package com.core.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

/**
  - Все исключения являются наследниками суперкласса Throwable и его подклассов Error и Exception из пакета java.lang.
  - Исключительные ситуации типа Error возникают только во время выполнения программы. Такие исключения, связанные с серьезными ошибками,
     к примеру, с переполнением стека, не подлежат исправлению и не могут обрабатываться приложением. Собственные подклассы от Error создавать мало смысла
    по причине невозможности управления прерываниями
  - Exception и его наследники (исключая RuntimeException с наследниками) - проверяемые исключения
     Возможность возникновения проверяемого исключения может быть отслежена еще на этапе компиляции кода. Компилятор проверяет,
     может ли данный метод генерировать или обрабатывать исключение. Проверяемые исключения должны быть обработаны в методе,
     который может их генерировать, или включены в throws-список метода для дальнейшей обработки в вызывающих методах.
  - правила для проверяемых исключений при наследовании:
    1) переопределяемый метод в подклассе не может содержать в инструкции throws исключений,
        не обрабатываемых в соответствующем методе суперкласса (cm PolymorphMethodException);
        Если при объявлении метода суперкласса инструкция throws присутствует, то в подклассе эта инструкция может вообще отсутствовать или в ней могут быть
        объявлены только исключения, являющиеся подклассами(!) исключения из секции throws метода суперкласса
    2) конструктор подкласса должен включить в свою секцию throws все классы исключений или их суперклассы(!) из секции throws конструктора
        суперкласса, к которому он обращается при создании объекта. Это позволяет защитить программиста от возникновения неизвестных ему исключений при создании объекта.

  - Если метод генерирует исключение с помощью оператора throw и при этом блок catch в методе отсутствует,
    то для передачи обработки исключения вызывающему методу тип проверяемого (checked) класса исключений должен быть
    указан в операторе throws при объявлении метода
  - класс RuntimeException и порожденные от него классы относятся к непроверяемым исключениям. Компилятор не проверяет,
     может ли генерировать и/или обрабатывать метод эти исключения. Исключения типа RuntimeException генерируются
    при возникновении ошибок во время выполнения приложения. Их обработка должна быть предусмотрена программистом.
  - Код блока finally выполняется перед выходом из метода даже в том случае, если перед ним были выполнены такие инструкции, как
    throws, return, break, continue.


 */
public class ExceptionTest {
    public static void main(String[] args) {
        try {
        } catch (PatternSyntaxException e) { //..
        } catch (IllegalArgumentException e) { //..
        }

        // В catch не могут находиться исключения из одной иерархической цепочки
        /*try {
        } catch(PatternSyntaxException | IllegalArgumentException e) { //..
        }*/

        try {
            Class<?> goatClass = Class.forName("com.core.LocaleTest");
            goatClass.getConstructor().newInstance();
        } catch (NumberFormatException | ClassNotFoundException | InstantiationException | NoSuchMethodException |
                 IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

         try {
            try {
                load(ResourceBundle.getBundle(""));
            } catch (MissingResourceException ex) {
               throw new ResourceException("Can't find bundle.", ex);
            }
        } catch (ResourceException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

        System.out.println("------Test----------");
        try {
            method();
        } catch (ArithmeticException e) {
            System.out.println("E0");
        }

    }

    public static void load(ResourceBundle resource) throws ResourceException {
        if (resource == null) {
            throw new ResourceException("resource is null");
        }
        // more code
    }

    static void method() throws ArithmeticException {
        int i = 7 / 0;
        try {
            double d = 77.0;
            d /= 0.0;
        } catch (ArithmeticException e) {
            System.out.print("E1");
        } finally {
            System.out.print("Finally ");
        }
    }
}

class ColorException extends Exception {}
class WhiteException extends ColorException {}
class Color {
     void method() throws ColorException {}
}
class White extends Color {
    void method() throws WhiteException { // только потомок. Exception - ошибка
        throw new WhiteException();
    }
}

class A{
    A() throws IOException {}
}
class B extends A {

    B() throws Exception { // OK
    }

/*
    B() throws FileNotFoundException { //допустим только суперкласс
    }
*/

}