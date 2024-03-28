package com.core.collection.list;

import java.util.*;

import static com.core.collection.list.SetTest.Country.*;

/**
 * <p> Множества
 * <p> Интерфейс Set<E> объявляет поведение коллекции, не допускающей дублирования элементов. Наследует extends Collection<E>
 * <p> Интерфейсы SortedSet наследует Set<E> и объявляет поведение набора, отсортированного в возрастающем порядке, заранее определенном для класса.
 * <p> NavigableSet наследует SortedSet. Существенно облегчает поиск элементов, например, расположенных рядом с заданным.
 *
 * <p> TreeSet наследует AbstractSet, реализует NavigableSet
 * <p> Элементы должны реализовывать Comparable. При добавлении объекта в дерево он сразу же размещается в необходимую
 * позицию с учетом сортировки. Для хранения объектов использует бинарное (красно-черное) дерево. Используется TreeMap где элемент является ключом.
 * Не допускает дубликатов.
 * Обработка операций удаления и вставки объектов происходит несколько медленнее, чем в хэшмножествах, где при любом числе элементов время этих операций постоянно.
 * <p> Конструкторы
 * <p> TreeSet()
 * <p> TreeSet(Collection <? extends E> c)
 * <p> TreeSet(Comparator <? super E> c)
 * <p> TreeSet(SortedSet <E> s)
 * <p> Методы
 * <p> headSet(o)/headSet(E toElement, boolean inclusive) — возвращает представление (класс SortedSet) части этого набора, элементы которого строго меньше объекта o.
 * Элемент может быть произвольным, и не быть в множестве.
 * <p> subSet(j, k)/subSet(E fromElement, boolean fromInclusive, E toElement,   boolean toInclusive) —
 * возвращает представление (класс SortedSet) части набора, элементы которого расположены от объекта j , включая объект k.
 * <p> tailSet(o)/tailSet(E fromElement, boolean inclusive) — возвращает представление (класс SortedSet) части набора, элементы которого не меньше объекта о .
 *
 * <p> HashSet наследует AbstractSet, реализует Set. Использует хэш-таблицу для хранения коллекции. Не является сортированным.
 * Может хранить NULL – значения. Данные хранятся в HashMap как ключи.
 *
 * <p> LinkedHashSet разширяет HashSet, реализует Set, не добавляя никаких новых методов. LinkedHashSet поддерживает связный список элементов набора в том порядке,
 * в котором они вставлялись. Это позволяет организовать упорядоченную итерацию вставки в набор.
 * Но это приводит к тому что класс LinkedHashSet выполняет операции дольше чем класс HashSet. Данные хранятся в LinkedHashMap как ключи.
 *
 * <p> EnumSet наследует AbstractSet. Абстрактный. Экземпляр создается статическими методами of(...).
 * Класс специально реализован для работы с типами enum. Все элементы такой коллекции должны принадлежать единственному типу enum
 * <p> Методы
 * <p> EnumSet<E> noneOf(Class<E> elemType) cоздает пустое множество нумерованных констант с указанным типом элемента
 * <p> allOf(Class<E> elementType) создает множество нумерованных констант, содержащее все элементы указанного типа.
 * <p> of(E first, E… rest) создает множество, первоначально содержащее ука занные элементы.
 * <p> complementOf(EnumSet<E> s) создается множество, содержащее все элементы, которые отсутствуют в указанном множестве.
 * <p> range(E from, E to) создает множество из элементов, содержащихся в диапазоне, определенном двумя элементами.
 * <p>
 */
public class SetTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("2Y", "8Y", "8Y", "6Y", "5Y", "Y-");
        // System.out.println(list);
        TreeSet<String> treeSet = new TreeSet<>(list);
        treeSet.add("Y-");
        //treeSet.add(null);
        System.out.println(treeSet);

        System.out.println("1) " + treeSet.headSet("434"));
        System.out.println("2) " + treeSet.higher("2"));
        System.out.println("3) " + treeSet.tailSet("8Y", false));
        System.out.println("4) " + treeSet.subSet("5Y", "8Y"));
        System.out.println("5) " + treeSet.subSet("5Y", false, "8Y", true));

        //вариант хранения отсортированных не уникальных значений
        TreeSet<String> treeSet2 = new TreeSet<>((o1, o2) -> o1.compareTo(o2) == 0 ? 1 : o1.compareTo(o2) );
        treeSet2.addAll(list);
        treeSet2.add("Y-");
        System.out.println(treeSet2);

        enumSetExample();

    }

    private static void enumSetExample() {
        System.out.println("----enumSetExample------");
        EnumSet<Country> asiaCountries = EnumSet.of(ARMENIA, INDIA, KAZAKHSTAN);
        String nameCountry = "Belarus";
        Country current = Country.valueOf(nameCountry.toUpperCase());
        if (asiaCountries.contains(current)) {
            System.out.println(current + " is in Asia");
        } else {
            System.out.println(current + " is not in Asia");
        }

        EnumSet<Country> emptySet = EnumSet.noneOf(Country.class);
        System.out.println("emptySet: "+ emptySet.size());

        EnumSet<Country> setFromCollect = EnumSet.copyOf(Arrays.asList(ARMENIA, INDIA));
        System.out.println(setFromCollect);

        EnumSet<Country> setComplementOf = EnumSet.complementOf(setFromCollect);
        System.out.println(setComplementOf);

        EnumSet<Country> setAllOf = EnumSet.allOf(Country.class);
        System.out.println(setAllOf);

        EnumSet<Country> setRange = EnumSet.range(BELARUS, POLAND);
        System.out.println(setRange);

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

    enum Country {
        ARMENIA, BELARUS, INDIA, KAZAKHSTAN, POLAND, UKRAINE
    }
}
