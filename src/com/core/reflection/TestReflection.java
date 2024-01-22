package com.core.reflection;

import com.core.inner.Simple;

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


 */
    public static void main(String[] args) {
        fieldSearch();
        createInstance();
    }

    private static void fieldSearch() {
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
    }

    public static void createInstance() {
        MyClass myClass = null;
        try {
            System.out.println(MyClass.class.getName());
            System.out.println(MyClass.class.getSimpleName());
            System.out.println(MyClass.class.getCanonicalName());
            System.out.println(Simple.class.getCanonicalName());
            Class clazz = Class.forName(MyClass.class.getName());
            myClass = (MyClass) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(myClass);//output created object reflection.MyClass@60e53b93
    }

    public static void printData(Object myClass){
        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

class MyClass {
    private int number;
    private String name = "default";
    public String publicName = "default publicName";
    //    public MyClass(int number, String name) {
//        this.number = number;
//        this.name = name;
//    }
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

    @Override
    public String toString() {
        return "MyClass{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", publicName='" + publicName + '\'' +
                '}';
    }
}