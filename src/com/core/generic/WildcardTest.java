package com.core.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 - Конструкция <? super A> говорит компилятору, что метод может принимать на вход тип (коллекцию объектов) класса A либо любого другого класса-предка A.
   List<? super A> list;
   MyClass<? super A> myclass;
 - super допускается только в объявлении параметизированных методов
 */
public class WildcardTest {
    public static void main(String[] args) {
        A a = new A();
        BfromA bfromA = new BfromA();
        CfromBfromA cfromBfromA = new CfromBfromA();
        B1fromBfromA b1fromBfromA = new B1fromBfromA();

        extendsTest(a);
        extendsTest(bfromA);
        extendsTest(cfromBfromA);
        extendsTest(b1fromBfromA);

        superTest(Arrays.asList(cfromBfromA)); // list form Objects
        List<CfromBfromA> cfromBfromAList = new ArrayList<>();
        cfromBfromAList.add(cfromBfromA);
        //superTest(cfromBfromAList);

        List<BfromA> b1fromBfromAList = new ArrayList<>();
        b1fromBfromAList.add(b1fromBfromA);
        b1fromBfromAList.add(cfromBfromA);
        superTest(b1fromBfromAList);

        List<A> aList = new ArrayList<>();
        aList.add(a);
        superTest(aList);
        List<BfromA> bfromAList = new ArrayList<>();
        bfromAList.add(bfromA);
        superTest(bfromAList);

        GenericNum2<Integer> g1 = new GenericNum2<>(Integer.valueOf(56));
        testSuper(g1);
        GenericNum2<Number> g2 = new GenericNum2<>(Double.valueOf(43.343));
        testSuper(g2);
        GenericNum2<Double> g3 = new GenericNum2<>(Double.valueOf(43.343));
        //testSuper(g3);
    }

    private static <T extends A> void extendsTest(T a) {}

    private static void extendsTest(List<? extends A> a) {}

    //private static <T super BfromA> void superTest(T a) {}

    private static void superTest(List<? super BfromA> a) {}

    private static  void testSuper(GenericNum2<? super Integer> g) {
        System.out.println("--testSuper--");
        System.out.println(g.number);
        System.out.println("-------------");
    }

}

class A{}
class BfromA extends A{}
class CfromBfromA extends BfromA{}

class B1fromBfromA extends BfromA{}

class GenericNum2<T extends Number> {
    T number;

    public GenericNum2(T number) {
        this.number = number;
    }
}
