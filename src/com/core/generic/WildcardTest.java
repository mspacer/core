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
 Параметризация List<? super A> утверждает, что параметр метода или возвращаемое значение может получить список типа A или любого из его
 суперклассов, в то же время разрешает добавлять туда экземпляры класса A и любых его подклассов

 Eсли параметр метода List<? extends A>, то в метод можно будет передавать коллекции, параметризованные любым допустимым типом,
 а именно классом A и любым его подклассом, что невозможно при записи без анонимного символа.
 Но в методе нельзя будет добавить к коллекции новый элемент, пусть даже и допустимого типа, так как компилятору неизвестен заранее тип параметризации списка.
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
        bfromAList.add(b1fromBfromA);
        superTest(bfromAList);

        extendsTestList(aList);
        extendsTestList(bfromAList);
        extendsTestList(cfromBfromAList);

        GenericNum2<Integer> g1 = new GenericNum2<>(Integer.valueOf(56));
        testSuper(g1);
        GenericNum2<Number> g2 = new GenericNum2<>(Double.valueOf(43.343));
        testSuper(g2);
        GenericNum2<Double> g3 = new GenericNum2<>(Double.valueOf(43.343));
        //testSuper(g3);
    }

    private static <T extends A> void extendsTest(T a) {}

    private static void extendsTestList(List<? extends A> a) {
        /* нельзя добавить к коллекции новый элемент, пусть даже
        и допустимого типа, так как компилятору неизвестен заранее тип параметризации списка.
        Поэтому добавление к спискам, параметризованным метасимволом с применением extends, запрещено всегда*/
        //a.add(new A());
        //a.add(new BfromA());

        //в списке все элементы имеют базовый тип А
        a.get(0).methodFromA();
    }

    //private static <T super BfromA> void superTest(T a) {}

    private static void superTest(List<? super BfromA> a) {
        /* В этом случае к списку, возвращенному методом, можно будет добавлять
        экземпляры класса BfromA и его подклассов. */
        //a.add(new A()); - error
        a.add(new B1fromBfromA());
        a.add(new BfromA());

        //поскольку в метод могут передаваться список с предками BfromA и какой именно предок неизвестно, доступ есть
        //только к методам класса Object.
        a.get(0).getClass();
    }

    private static  void testSuper(GenericNum2<? super Integer> g) {
        System.out.println("--testSuper--");
        System.out.println(g.number);
        System.out.println("-------------");
    }

}

class A{
    public void methodFromA() {};
}
class BfromA extends A{
    public void methodFromB() {};
}
class CfromBfromA extends BfromA{
    public void methodFromC() {};
}

class B1fromBfromA extends BfromA{}

class GenericNum2<T extends Number> {
    T number;

    public GenericNum2(T number) {
        this.number = number;
    }
}
