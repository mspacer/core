package com.core;

import java.util.Arrays;

public class SystemTest {
    public static void main(String[] args) {
        //System.arraycopy - копирует один массив в другой с учетом позиций и размера
        //Существующие данные будут заменены
        Integer[] src = {1, 2, 4, 5, 6, 9};
        Integer[] dest = new Integer[20];//{11, 12, 13, 15, 16, 19};
        System.arraycopy(src, 0, dest, 5, src.length);
        System.out.println(Arrays.asList(dest));

        Data[] lsrc = new Data[10];
        for (int i = 0; i < 10; i++) {
            lsrc[i] = new Data(i + 10);
        }
        Data[] ldest = new Data[20];
        for (int i = 0; i < 10; i++) {
            ldest[i] = new Data(22 + 10);
        }

        System.arraycopy(lsrc, 0, ldest, 5, 3);
        System.out.println(Arrays.asList(ldest));
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
