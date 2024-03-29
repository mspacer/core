package com.core.collection.map;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * <p> В ряде распределенных приложений, например, с использованием сервлетов, до сих пор применяются коллекции более медленные в обработке,
 * но при этом потокобезопасные (thread-safety), существовавшие в языке Java с момента его создания, а именно карта Hashtable<K, V>,
 * список Vector<E> и итератор java.util.Enumeration<E>. Все они также были параметризованы и могут быть обработаны с помощью stream,
 * но сохраняют безопасность от одновременного доступа из конкурирующих потоков.
 * <p> Класс Hashtable<K, V> реализует интерфейс Map, обладает также несколькими специфичными, по сравнению с другими коллекциями, методами:
 * <p> Enumeration<V> elements() — возвращает итератор для значений карты;
 * <p> Enumeration<K> keys() — возвращает итератор для ключей карты.
 * <p>
 */
public class HashtableTest {
    public static void main(String[] args) {
        hashTableExm();
        vectorExm();
    }

    private static void vectorExm() {
        System.out.println("----vectorExm----");
        Vector<String> vector = new Vector<>(777);
        vector.add("java");
        vector.add("epam");
        vector.add(1, null);
        vector.addAll(vector);
        System.out.println(vector);

        vector.removeIf(e -> e == null);
        vector.replaceAll(String::toUpperCase);
        System.out.println(vector);

        long size = vector.stream().count();
        System.out.println(size);

        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.printf("%s ", enumeration.nextElement());
        }
    }

    private static void hashTableExm() {
        System.out.println("---hashTableExm---");
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("Jeans", 40); // adding a pair
        table.put("T-Shirt", 35);
        table.put("Gloves", 42);
        table.compute("Shoes", (k, v) -> 77); // adding a pair
        System.out.println(table);

        Enumeration<String> keys = table.keys();
        while (keys.hasMoreElements()) {
            System.out.println(keys.nextElement());
        }

        Enumeration<Integer> values = table.elements();
        while (values.hasMoreElements()) {
            System.out.println(values.nextElement());
        }
    }
}
