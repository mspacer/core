package com.core.filestream;

import java.io.*;
import java.util.Arrays;

public class ReaderTest {
    public static void main(String[] args) {
        try {
            fileReader();
            fileReader2();
            bufferedReader();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void fileReader() throws IOException {
        FileReader f = new FileReader("data/out3.txt");
        System.out.println(f.getEncoding()); //default utf-8

        char buf[] = new char[100];
        f.read(buf);
        for (char c : buf) {
            if ((int) c > 0)
                System.out.print(c);
        }
        f.close();
        System.out.println();
        System.out.println();
    }

    private static void fileReader2() throws IOException {
        InputStreamReader f = new InputStreamReader(new FileInputStream("data/out2.txt"), "cp1251");
        System.out.println(f.getEncoding());
        boolean notEof = true;

        while (notEof) {
            char buf[] = new char[10];
            f.read(buf);
            for (char c : buf) {
                if ((int) c > 0) {
                    System.out.print(c);
                } else {
                    notEof = false;
                   break;
                }
            }
        }
        f.close();
    }

    private static void bufferedReader() throws IOException {
        System.out.println("------BufferedReader-----------");
        InputStreamReader f = new InputStreamReader(new FileInputStream("data/out2.txt"), "cp1251");
        BufferedReader bf = new BufferedReader(f);
        bf.lines().forEach(System.out::println);
        bf.close();
    }

}
