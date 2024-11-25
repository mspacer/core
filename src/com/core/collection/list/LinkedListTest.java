package com.core.collection.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <p>Реализует, кроме интерфейсов, указанных при описании ArrayList, также интерфейсы Queue<E> и Deque<E>.
 * <p>поля класса: int size, Node<E> first, last.
 * <p>Node: E item, Node<E> next, prev
 * <p>Связанный список хранит ссылки на объекты отдельно вместе со ссылками на следующее и предыдущее звенья последовательности,
 * поэтому часто называется двунаправленным списком.
 * <p> Имеет методы доступа по индексу как List и прямого доступа к первому и последнему как Queue или Deque.
 * <p>Самый быстрый метод класса add(E element). Главным же достоинством класса является скорость работы метода remove() на Iterator,
 * после получения его из LinkedList. Также очень быстро работает метод add(E element) на ListIterator.
 * <p>Операция удаления из начала и конца списка выполняется достаточно быстро, в отличие от операций поиска и извлечения.
 * <p>LinkedList быстрее, чем ArrayList, при добавлении в середину списка методом add() в 2 раза, а в начало или конец примерно в 40 раз.
 * <p>Вставки и удаления элементов из LinkedList происходят за постоянное время, в том числе и с использованием итераторов,
 * в то же время вставка\удаление элемента в ArrayList приводит к сдвигу всех элементов после позиции добавления\удаления, а в случае, если базовый массив
 * хранения переполняется, то еще и сам массив увеличивается в полтора раза с копированием старого массива в новый.
 * <p>Список ArrayList, в свою очередь, быстрее при вызове метода get(index) примерно в 50 раз.
 * <p>Список LinkedList занимает от 3,5 до 5 раз больше памяти нежели аналогичный список ArrayList за счет необходимости
 * хранения ссылок на соседние объекты.
 * <p>Если необходимо осуществлять быструю навигацию по списку, то следует применять ArrayList, так как перебор элементов в LinkedList
 * осуществляется на порядок медленнее.
 * <p>Если требуется часто добавлять и удалять элементы из списка, то уже класс LinkedList обеспечивает значительно более высокую скорость переиндексации.
 * <p>
 * <p>
 */
public class LinkedListTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>() {
            {
                this.offer("Jeans");
            }
        };
        queue.add("Dress");
        queue.offer("Gloves");
        queue.offer("T-Shirt");
        //queue.stream().filter(s -> !s.endsWith("s")).forEach(System.out::println);
        queue.removeIf(s -> !s.endsWith("s"));
        System.out.println("_____");
        queue.forEach(System.out::println);

        LinkedList<String> prior = new LinkedList<>();
        prior.offer("J");
        prior.offer("A");
        prior.offer("V");
        prior.offer("A");
        prior.offer("1");
        prior.offer("4");
        prior.stream()
                .map(s -> s + ", ")
                .forEach(System.out::print);
        System.out.println();
        Iterator<String> stringIterator = prior.descendingIterator();
        while (stringIterator.hasNext()) {
            System.out.print(stringIterator.next() + ", ");
        }
        System.out.println();
        System.out.println(prior.peekFirst() + "/" + prior.peekLast());
    }
}
