package com.core.generic;

import com.bean.Message;
import com.bean.Post;
import com.bean.SimpleAction;
import com.bean.Task;

public class GenericTest<T> {

    // Недопустимо, т.к. неизвесный компилятору класс Т
    //private T t = new T();
    //static T t;
    //static void takeKey(T t) {}

    public static void main(String[] args) {
        instantsOffExample();

        genericObjectEqualExample();

        methodGenericCompare();

        methodGenericExample();

    }

    private static void methodGenericExample() {
        System.out.println("methodGenericExample");
        SimpleAction action = new SimpleAction(Integer.valueOf(100));
        action.printReport("new Course(42)");

        // error java: valueOf(int) in java.lang.Integer is defined in an inaccessible class or interface
        // В качестве параметров классов запрещено применять базовые типы.
        //action.calculateMark(10);

        System.out.println(action.calculateMark(new Integer(10)));

        /* Создание экземпляра с использованием параметризованного конструктора
            без параметров требует указания типа параметра перед именем конструктора */
        action = new <String>SimpleAction();
        action.<Integer>check();
        System.out.println("____________");
    }

    private static void methodGenericCompare() {
        System.out.println("methodGenericCompare");

        Task<Double> task1 = new Task(71.41D,"JSE");// 71.5d
        Task<Double> task2 = new Task(71.45D, "JEE");// 71.5d
        System.out.println(task1.equalsToMark(task2));

        Task<Integer> task = new Task(71,"Scala");
        // task1.equalsToMark(task); // compile error: incompatible types
        System.out.println(task1.equalsToMarkAny(task));
        System.out.println("____________");
    }

    private static void genericObjectEqualExample() {
        System.out.println("genericObjectEqualExample");

        Message<Integer> ob1 = new Message<>();
        ob1.setValue(1); // only Integer or int
        int v1 = ob1.getValue();
        System.out.println(v1);

        Message<String> ob2 = new Message<>("Java");
        String v2 = ob2.getValue();
        System.out.println(v2);

        // ob1 = ob2; // compile error – parameterization is not covariant

        // default parameterization – Object
        Message ob3 = new Message(); // warning – raw type
        ob3 = ob1; // no compilation error - no parameterization
        //ob1 = ob3; no compilation error
        System.out.println(ob3.getValue());

        ob3.setValue(new Byte((byte) 1));
        ob3.setValue("Java SE 12");
        System.out.println(ob3);/* the type of the object is displayed, not the type of parameterization */

        ob3.setValue(71);
        System.out.println(ob3);
        ob3.setValue(null);

        GenericNum<Integer> i1 = new GenericNum<>(500);
        GenericNum<Integer> i2 = new GenericNum<>(500);
        System.out.print(i1.get() == i2.get());
        System.out.print(" ");
        System.out.println(i1.get().intValue() == i2.get().intValue());
        System.out.println("____________");
    }

    private static void instantsOffExample() {
        System.out.println("instantsOffExample");

        Post<Integer, String> post = new Post<>();

        if (post instanceof Post/*<Integer, String>*/) {
            System.out.println("is instance");
        }
        System.out.println("____________");
    }

}

class GenericNum<T extends Number> {
    T number;
    GenericNum(T t) {
        number = t;
    }
    T get() {
        return number;
    }
}


