package com.core.collection;

import java.util.*;

/**
 * <p> Collections - утилитный класс. Методы:
 * <p> addAll(colls, e1, e2, e3, ..)  Добавляет в коллекцию colls элементы e1, e2, e3,...
 * <p> Queue<T> asLifoQueue(Deque<T> deque) - создает очередь по типу LIFO (см ArrayDequeTest)
 * <p> fill(list, obj) Заменяет в переданном списке все элементы на obj
 * <p> nCopies(n, obj) Возвращает список, состоящий из n копий объекта obj
 * <p> replaceAll(list, oldVal, newVal) Заменяет в списке list все значения oldVal на newVal
 * <p> copy(dest, src) Копирует все элементы из списка src в список dest
 * <p> reverse(list) Разворачивает список задом наперед
 * <p> sort(list) Сортирует список в порядке возрастания
 * <p> sort(List<T> list, Comparator<? super T> c) — сортировка списка естественным порядком и с использованием Comparable или Comparator соответственно;
 * <p> rotate(list, n) Циклично сдвигает элементы списка list на n элементов
 * <p> shuffle(list) Случайно перемешивает элементы списка
 * <p> void swap(List<?> list, int i, int j) — меняет местами элементы списка, стоящие на заданных позициях;
 * <p> min(colls) Находит минимальный элемент коллекции colls
 * <p> max(colls) Находит максимальный элемент коллекции colls
 * <p> frequency(colls, obj) Определяет, сколько раз элемент obj встречается в коллекции colls
 * <p> binarySearch(list, key) Ищет элемент key в отсортированном списке, возвращает индекс.
 * <p> disjoint(colls1, colls2) Возвращает true, если у коллекций нет общих элементов
 * <p> <T> List <T> emptyList(), <K, V> Map <K, V> emptyMap(), <T> Set <T> emptySet() — возвращают пустой список, карту отображения и множество соответственно;
 * <p> singleton(T o), singletonList(T o), singletonMap(K key, V value) — создают множество, список и карту отображения, позволяющие добавлять только один элемент;
 * <p> <T> List<T> unmodifiableList(List<? extends T> list) — возвращает ссылку на список с запрещением его модификации. Аналогичные методы есть для всех коллекций.
 * <p>
 */
public class CollectionsTest {
    public static void main(String[] args) {
        final List<String> bonds = Arrays.asList("BOND_GOS_REG_MAIN", "BOND_GOS_REG_ADD", "BOND_ID_MAIN", "BOND_ID_ADD");
        final List<String> programs = Collections.singletonList("BOND_PROG");

        if (bonds.contains("BOND_ID_MAIN")) {
            System.out.println(bonds.indexOf("BOND_ID_MAIN"));
        }
        if (!programs.contains("BOND_ID_MAIN")) {
            System.out.println("not contains");
        }

        List<String> annulNums = new ArrayList();
        String[] nums = "01-10602-Z;05-10602-Z;06-10602-Z;08-10602-Z".split(";");
        for (String num : nums) {
            if (!num.contains("06-10602-Z")) {
                annulNums.add(num);
            }
        }
        System.out.println(annulNums);

        List<String> unmodifiableList = Collections.unmodifiableList(annulNums);
        //unmodifiableList.add("unmodifiableList"); //UnsupportedOperationException

        List<String> immutableList = Collections.singletonList("One");
        System.out.println("immutableList: " + immutableList.getClass());
        //immutableList.add("Two"); //UnsupportedOperationException
        System.out.println("immutableList: " + immutableList);

        System.out.println("Search");
        System.out.println(Collections.binarySearch(bonds, "BOND_ID_MAIN"));

        System.out.println("Sorting");
        List<String> aNums = Arrays.asList(nums);
        Collections.sort(aNums, (o1, o2) -> o1.compareTo(o2));
        System.out.println(aNums);

        System.out.println("Copy");
        //если не указать размер - IndexOutOfBoundsException. В конструктор передается initialCapacity. size = 0
        List<String> aNums2 = new ArrayList<>(aNums.size());
        // необходимо добавлять значения
        for (int i = 0; i < aNums.size(); i++) {
            aNums2.add("");
        }
        Collections.copy(aNums2, aNums);
        System.out.println(aNums2);
        //Проще использовать конструктор списка
        List<String> aNums3 = new ArrayList<>(aNums);
        System.out.println(aNums3);

    }

    private static class Data implements Comparable<Data> {
        int pos;

        Data(int pos) {
            this.pos = pos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return pos == data.pos;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pos);
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
