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
 * <p> Дополнительные методы:
 * <p> ensureCapacity(int minCapacity) - увеличивает размер внутреннего массива
 * <p> forEach(Consumer<? super E> action) - альтернатива итератору
 * <p> trimToSize() - приводит размер внутреннего массива к size
 * <p>
 * <p> Добавление, удаление, вставка происходит через вызов System.arraycopy(...)
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
        list1.add(new Data(22));
        //list1.add(null); разрешается вставка null
        //list1.add(null);
        list1.removeIf(data -> data.pos == 22);

        System.out.println(list1);

        // создается новый массив
        //Data[] arrList1 = list1.toArray(new Data[0]);
        Data[] data1 = new Data[list1.size()];
        //копирование в data1. Он же и возвращается.
        Data[] arrList1 = list1.toArray(data1);
        System.out.println("data1 == arrList1: " + (data1 == arrList1));
        System.out.println("arrList1.length: " + arrList1.length);

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

        list1.add(new Data(14));
        list1.add(new Data(15));
        list1.add(new Data(16));

        for (Data d: list1) {
            //ConcurrentModificationException
            //list1.remove(d);
            //list1.add(new Data(5));
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

