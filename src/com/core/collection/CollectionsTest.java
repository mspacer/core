package com.core.collection;

import java.util.*;

/**
 * <p> Collection - Базовый интерфейс. Наследники-интерфейсы: Set, List, Queue (Deque)
 * <p> Map - отдельная базовая коллекция</p>
 * <p> Доступ по индексу (get(i)) есть только у List
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

        List<String> annulNums2 = new ArrayList();
        // annulNums2.addAll(Collections.singletonList(nums));

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
