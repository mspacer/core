package com.core.types;

public class BiteOperationTest {
    public static void main(String[] args) {
        int initialCapacity = 10;
        initialCapacity |= (initialCapacity >>>  1);
        // 10 = 1010, >>> 1 = 0101 , | - ИЛИ:  1010 + 0101 = 1111 = 15
        System.out.println(initialCapacity);

        initialCapacity &= (initialCapacity >>>  1);
        // 15 >>> 1 = 111, & - И:  1111 + 0111 = 111 = 7
        System.out.println(initialCapacity);

    }
}
