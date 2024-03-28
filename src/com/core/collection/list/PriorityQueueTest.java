package com.core.collection.list;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <p> Упорядоченная очередь. Наследуется от AbstractQueue -> AbstractCollection
 * <p> По умолчанию компаратор размещает элементы в очереди в порядке возрастания. Элементы должны быть Comparable.
 * Есть конструктор, принимающий Comparator.
 * <p> Данные хранятся в массиве Object[] queue, но доступа по индексу нет. Можно получить только первый элемент.
 * <p> Если порядок в классе не определен, а именно, не реализован ни один из указанных интерфейсов, то генерируется исключительная ситуация ClassCastException
 * при добавлении второго элемента в очередь. При добавлении первого элемента компаратор не вызывается, так как сравнивать объект не с чем.
 * <p> Позволяет дубликаты. Не допускается NULL
 * <p>
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<String> prior = new PriorityQueue<>(/*Comparator.reverseOrder()*/);
        prior.offer("J");
        prior.offer("A");
        prior.offer("V");
        prior.offer("A");
        prior.offer("1");
        prior.offer("4");
       // prior.offer(null);
        prior.stream()
                .map(s -> s + ", ")
                .forEach(System.out::print);

        System.out.println();

        while (!prior.isEmpty()) {
            System.out.println(prior.poll());
        }

        Queue<Data> priorData = new PriorityQueue<>();
        priorData.add(new Data(10));
        priorData.add(new Data(2)); //ClassCastException
        priorData.add(new Data(20));

        System.out.println(priorData.peek());

        while (!priorData.isEmpty()) {
            System.out.println(priorData.poll());
        }

        PriorityQueue<StringBuilder> orders = new PriorityQueue<>(Comparator.comparing(StringBuilder::toString));
        orders.add(new StringBuilder("Oracle")); // ok
        orders.add(new StringBuilder("Google")); // ok

        while (!orders.isEmpty()) {
            System.out.println(orders.poll());
        }
    }

    private static class Data implements Comparable<Data> {
        int pos;
        Data(int pos) {
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "pos=" + pos +
                    '}';
        }

        @Override
        public int compareTo(Data o) {
            return this.pos - o.pos;
        }
    }
}


