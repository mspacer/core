package com.core.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;

/**
 * <p> Наследуется от AbstractList(implements List) -> AbstractCollection -> Collection
 * <p> Данные хранятся в Object[] elementData;
 * <p> Конструкторы:
 * <p> ArrayList() - elementData  = {}. После первого добавления размер массива - DEFAULT_CAPACITY = 10
 * Далее к длине массива прибавляется половина его длины (сдвиг на один бит вправо)
 * <p> ArrayList(int initialCapacity) - начальный размер elementData
 * <p> ArrayList(Collection<? extends E> c) - входящая коллекция копируется в elementData
 * <p> Добавление, удаление, вставка происходит через вызов System.arraycopy(...)
 * <p> E get(int index) — возвращает элемент в виде объекта из позиции index, представляет собой одно из главных достоинств класса из-за скорости выполнения;
 * <p> void add(int index, E element) — вставляет element в позицию, указанную в index;
 * <p> E remove(int index) — удаляет объект из позиции index;
 * <p> boolean removeIf(Predicate<? super E> filter) — удаляет по условию (Collection);
 * <p> E set(int index, E element) — заменяет объект в позиции index, возвращает при этом удаляемый элемент;
 * <p> boolean addAll(int index, Collection<? extends E> c) — вставляет в вызывающий список все элементы коллекции с, начиная с позиции index;
 * <p> int indexOf(Object ob) — возвращает индекс указанного объекта;
 * <p> default void sort(Comparator<? super E> c) — сортирует список на основе компаратора;
 * <p> contains(Object o) - проверяет наличие объекта
 * <p> indexOf(Object o) - возвращает индекс первого совпадающего объекта
 * <p> List<E> subList(int fromIndex, int toIndex) — извлекает часть коллекциив указанных границах;
 * <p> static <E> List<E>copyOf(Collection <? extentds E> coll) — создает немодифицируемый список на основе передаваемой коллекции.
 * Java 10. Возвращает ImmutableCollection
 * <p> iterator() - возвращает итератор. Использование итератора после модификации коллекции приводит к ConcurrentModificationException
 * <p> forEach(Consumer<? super E> action) - альтернатива итератору
 * <p> boolean removeIf(Predicate<? super E> filter) - удаление элементов по условию
 * <p> replaceAll(UnaryOperator<E> operator) - заменяет каждый элемент результатом, возвращаемым operator.
 * <p>
 * <p> Удаление и добавление элементов для такой коллекции представляет ресурсоемкую задачу, поэтому объект ArrayList<E> лучше всего подходит для хра-
 * нения списков с малым числом подобных действий. С другой стороны, навигация по списку осуществляется очень быстро, поэтому операции поиска
 * производятся за более короткое время.
 * <p> Нельзя проводить одновременно итерацию (перебор) коллекции и изменение ее элементов. Альтернатива - использование Iterator
 * <pre>
 *     for (Integer i: list) {
 *          list.remove(i);
 *          //или
 *          list.add(i+1);
 *          // приводит к ConcurrentModificationException
 *     }
 * </pre>
 * <p>
 */
public class ArrayLIstTest {
    public static void main(String[] args) {
        ArrayList<Data> list1 = new ArrayList<>();
        list1.add(new Data(11));
        list1.add(new Data(12));
        list1.add(new Data(13));
        list1.add(new Data(14));
        list1.add(new Data(15));
        list1.add(new Data(16));
        list1.add(new Data(17));
        list1.add(new Data(18));
        list1.add(new Data(19));
        list1.add(new Data(20));
        list1.add(new Data(21));
        //list1.add(null); разрешается вставка null
        //list1.add(null);
        System.out.println(list1);

        System.out.println("forEach");
        list1.forEach(System.out::println);
        Spliterator<Data> spliterator = list1.spliterator();
        spliterator.getExactSizeIfKnown();

        list1.removeIf(o -> o.pos == 17);
        list1.forEach(o -> o.pos = o.pos + 100);

        System.out.println("iterator");
        Iterator<Data> iterator = list1.iterator();
        // приводит к ConcurrentModificationException
        //list1.add(new Data(1000));
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("replaceAll");
        list1.replaceAll(data -> {
            data.pos = data.pos - 100;
            return data;
        });
        System.out.println(list1);

        ListIterator<Data> dataListIterator = list1.listIterator();
        dataListIterator.next();
        dataListIterator.remove();
        System.out.println(list1);

        removeExample();
    }

    private static void removeExample() {
        System.out.println("-----removeExample-----");
        ArrayList<Data> list1 = new ArrayList<>();
        list1.add(new Data(11));
        list1.add(new Data(12));
        list1.add(new Data(13));
        list1.add(new Data(14));
        list1.add(new Data(15));
        list1.add(new Data(16));

        Iterator<Data> iterator = list1.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().pos > 13)
                iterator.remove();
        }
        System.out.println(list1);
    }

    private static class Data {
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
    }
}

