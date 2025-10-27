package com.core.reflection;

import com.core.inner.Simple;
import com.core.inner.SimpleStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflection {

 /**
 -  Рефлексия (от позднелат. reflexio — обращение назад) — это механизм исследования данных о программе во время её выполнения.
        Рефлексия позволяет исследовать информацию о полях, методах и конструкторах классов.
 -  Вот основной список того, что позволяет рефлексия:
    -   Узнать/определить класс объекта;
    -   Получить информацию о модификаторах класса, полях, методах, константах, конструкторах и суперклассах;
    -   Выяснить, какие методы принадлежат реализуемому интерфейсу/интерфейсам;
    -   Создать экземпляр класса, причем имя класса неизвестно до момента выполнения программы;
    -   Получить и установить значение поля объекта по имени;
    -   Вызвать метод объекта по имени.
 -  getFields() и getDeclaredFields() не возвращают поля класса-родителя
 - getCanonicalName для вложенных классов возвращает путь класса разделенный точкой (com.core.inner.SimpleStatic.SubNested)

 - обработка аннотаций см AnnotationTest
 */
    public static void main(String[] args) {
        fieldSearch();
        createInstance();
        constructorInfo();
        invokeStaticMethod();
    }

    private static void invokeStaticMethod() {
        System.out.println("--invokeStaticMethod--");
        Class<MyClass> clazz = MyClass.class;
        try {
            Method method = clazz.getMethod("greeting", String.class);
            Object result = method.invoke(null, "Eric");
            System.out.println("public static: " + result);

            method = clazz.getDeclaredMethod("goodBye", String.class);
            method.setAccessible(true);
            System.out.println("private static: " + method.invoke(null, "Serg"));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------");
    }

    private static void constructorInfo() {
        System.out.println("--constructorInfo--");
        Class<MyClass> clazz = MyClass.class;
        //Constructor[] constructors = clazz.getConstructors();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.print(constructor.getName() + " / ");
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class paramType : paramTypes) {
                System.out.print(paramType.getName() + " / ");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    private static void fieldSearch() {
        System.out.println("--fieldSearch--");
        MyClass myClass = new MyClass();
        int number = myClass.getNumber();
        System.out.println(number );//output 0null
        String name = null; //no getter =(

        try {
            // getField  - for public
            Field field = myClass.getClass().getDeclaredField("name");
            //разрешить работу с приватным полем
            field.setAccessible(true);
            name = (String) field.get(myClass);
            System.out.println(name);

            field.set(myClass, "new value");
            name = (String) field.get(myClass);
            System.out.println("changed name: " + name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        printData(myClass);
        System.out.println("----------------");
    }

    public static void createInstance() {
        System.out.println("--createInstance--");
        MyClass myClass = null;
        try {
            System.out.println(MyClass.class.getName());
            System.out.println(MyClass.class.getSimpleName());
            System.out.println(SimpleStatic.SubNested.class.getName());
            System.out.println(SimpleStatic.SubNested.class.getCanonicalName());

            Class clazz = Class.forName(MyClass.class.getName());
            myClass = (MyClass) clazz.newInstance();
            System.out.println(myClass);//output created object reflection.MyClass@60e53b93

            Class[] params = {int.class, String.class};
            Constructor constructor = clazz.getDeclaredConstructor(params);
            constructor.setAccessible(true);
            myClass = (MyClass) constructor.newInstance(1, "default2");
            System.out.println(myClass);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("----------------");
    }

    public static void printData(Object myClass) {
        System.out.println("--printData--");
        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("----------------");
    }

}

class MyClass {
    private int number;
    private String name = "default";
    public String publicName = "default publicName";

    public MyClass() {
    }

    private MyClass(int number, String name) {
        this.number = number;
        this.name = name;
        publicName =  name + "/" + number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void printData(){
        System.out.println(number + "/" + name);
    }

    public static String greeting(String name) {
        return String.format("Hey %s, nice to meet you!", name);
    }

    private static String goodBye(String name) {
        return String.format("Bye %s, see you next time.", name);
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", publicName='" + publicName + '\'' +
                '}';
    }
}