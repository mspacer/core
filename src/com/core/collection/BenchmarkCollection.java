package com.core.collection;

import java.util.*;

/**
 * <p> ArrayList vs LinkedList
 * <p> Метод	            Arraylist	LinkedList
 * <p> get(index)	        O(1)	    O(n)
 * <p> add(E)	            O(n)	    O(1)        - на больших списках Arraylist быстрее
 * <p> add(E, index)	    O(n)	    O(n)        - LinkedList значительно быстрее
 * <p> remove(index)	    O(n)	    O(n)        - LinkedList быстрее (из середины)
 * <p> Iterator.remove()	O(n)	    O(1)        - LinkedList значительно быстрее (из середины)
 *
 * <p> LinkedList vs HashSet
 * <p> встввка в середину быстрее HashSet
 * <p> Удвление из середины итератором быстрее LinkedList
 * <p>
 */
public class BenchmarkCollection {
    public static void main(String[] args) {
        //arrayList_LinkedList();
        //arrayList_HashSet();
        linkedList_HashSet();

    }

    private static void arrayList_LinkedList() {
        System.out.println("------arrayList_LinkedList------");
        int size = 400000;
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        System.out.println("--add--");
        long before = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--insert to and--");
        before = System.nanoTime();
        for (int i = size; i < size + 2000; i++) {
            arrayList.add(i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = size; i < size + 2000; i++) {
            linkedList.add(i);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--insert(i)--");
        before = System.nanoTime();
        for (int i = size; i < size + 20000; i++) {
            arrayList.add(20000, i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = size; i < size + 20000; i++) {
            linkedList.add(20000, i);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--get(i)--");
        before = System.nanoTime();
        arrayList.get(250000);
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        linkedList.get(250000);
        System.out.println(System.nanoTime() - before);

        System.out.println("--remove(i)--");
        before = System.nanoTime();
        for (int i = 10000; i < 20000; i++) {
            arrayList.remove(i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = 10000; i < 20000; i++) {
            linkedList.remove(i);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--remove iterator--");
        Iterator<Integer> aIterator = arrayList.iterator();
        int index = 0;
        before = System.nanoTime();
        while (aIterator.hasNext() && index < 30000) {
            aIterator.next();
            if (index++ >= 20000 ) {
                aIterator.remove();
            }
        }
        System.out.println(System.nanoTime() - before);

        Iterator<Integer> lIterator = linkedList.iterator();
        index = 0;
        before = System.nanoTime();
        while (lIterator.hasNext() && index < 30000) {
            lIterator.next();
            if (index++ >= 20000 ) {
                lIterator.remove();
            }
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("------end------");
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

    private static void arrayList_HashSet() {
        System.out.println("------arrayList_HashSet------");
        int size = 400000;
        ArrayList<Integer> arrayList = new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();

        System.out.println("--add--");
        long before = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--get(i)--");
        before = System.nanoTime();
        arrayList.get(250000);
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        hashSet.contains(250000);
        System.out.println(System.nanoTime() - before);
    }

    private static void linkedList_HashSet() {
        System.out.println("------linkedList_HashSet------");
        int size = 400000;
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();

        System.out.println("--add--");
        long before = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--insert(i)--");
        before = System.nanoTime();
        for (int i = size; i < size + 20000; i++) {
            linkedList.add(20000, i);
        }
        System.out.println(System.nanoTime() - before);

        before = System.nanoTime();
        for (int i = size; i < size + 20000; i++) {
            hashSet.add(20000);
        }
        System.out.println(System.nanoTime() - before);

        System.out.println("--remove iterator--");
        Iterator<Integer> lIterator = linkedList.iterator();
        int index = 0;
        before = System.nanoTime();
        while (lIterator.hasNext() && index < 30000) {
            lIterator.next();
            if (index++ >= 20000 ) {
                lIterator.remove();
            }
        }
        System.out.println(System.nanoTime() - before);

        Iterator<Integer> hIterator = hashSet.iterator();
        index = 0;
        before = System.nanoTime();
        while (hIterator.hasNext() && index < 30000) {
            hIterator.next();
            if (index++ >= 20000 ) {
                hIterator.remove();
            }
        }
        System.out.println(System.nanoTime() - before);
    }

}
