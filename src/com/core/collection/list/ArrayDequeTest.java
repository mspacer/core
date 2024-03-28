package com.core.collection.list;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p> ArrayDeque. Наследует extends AbstractCollection<E> и implements Deque<E>
 * <p> Интерфейс Deque определяет «двунаправленную» очередь и, соответственно, методы доступа к первому и последнему элементам двусторонней очереди.
 * Реализацию этого интерфейса можно использовать для моделирования стека.
 * <p> Данные хранятся в Object[] elements. Доступа по индексу нет. Есть доступ к первому и последнему элементу.
 * <p> Класс ArrayDeque<E> быстрее, чем Stack<E>, если используется как стек, и быстрее, чем LinkedList<E>, если используется в качестве очереди.
 * <p> Не допускается NULL. Дубли возможны.
 * <p>
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        //stack.push(null);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop()); // like as stack
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(11);
        queue.offer(22);
        queue.offer(33);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll()); // like as queue
        }
    }
}
