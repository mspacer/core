package com.core.collection.list;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

/**
 * <p> ArrayDeque. Наследует extends AbstractCollection<E> и implements Deque<E>
 * <p> Интерфейс Deque определяет «двунаправленную» очередь и, соответственно, методы доступа к первому и последнему элементам двусторонней очереди.
 * Реализацию этого интерфейса можно использовать для моделирования стека.
 * <p> Данные хранятся в Object[] elements. Доступа по индексу нет. Есть доступ к первому и последнему элементу.
 * <p> Класс ArrayDeque<E> быстрее, чем Stack<E>, если используется как стек, и быстрее, чем LinkedList<E>, если используется в качестве очереди.
 * <p> Не допускается NULL. Дубли возможны.
 * <p>
 * <p>
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        //вставка в голову
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        //stack.push(null);
        //получаем голову
        System.out.println("element: " + stack.element());
        System.out.println("stack: " + stack);

        while (!stack.isEmpty()) {
            //System.out.println(stack.pop()); // like as stack
            System.out.println(stack.poll());
        }


        Deque<Integer> queue = new ArrayDeque<>();
        //вставка в хвост
        queue.offer(11);
        queue.offer(22);
        queue.offer(33);

        //получаем голову
        System.out.println("element: " + queue.element());
        System.out.println("queue: " + queue);

        while (!queue.isEmpty()) {
            //System.out.println(queue.poll());
            System.out.println(queue.pop());
        }

        queue.add(111);
        queue.add(222);
        queue.add(333);
        Queue<Integer> lifo = Collections.asLifoQueue(new ArrayDeque<>());
        lifo.add(111);
        lifo.add(222);
        lifo.add(333);

        System.out.println("queue: " + queue);
        System.out.println("lifo: " + lifo);

    }
}
