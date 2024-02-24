package com.core.filestream;

import java.io.*;

public class ReaderTest {
    public static void main(String[] args) {
        try {
            inputStreamReader();
            bufferedReader();
            fileReader();
            fileReader2();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void inputStreamReader() throws IOException {
        System.out.println("-----inputStreamReader-------");
        FileInputStream fis = new FileInputStream("data/outStrmW.txt");
        InputStreamReader isr = new InputStreamReader(fis, "cp1251");

/*
        while(isr.ready()) {
            //System.out.print(isr.read() + ", ");
            System.out.print((char)isr.read());
        }
        System.out.println();
*/

        char[] cbuf = new char[100];
        int i;
        char c;

        while ((i = isr.read()) != -1) {
            // int to character
            c = (char) i;
            // print char
            System.out.print(c);
        }
        System.out.println();

        /*
        i = isr.read(cbuf);
        System.out.println(i);

        for (char c : cbuf) {
            // for empty character
            if (((int) c) == 0)
                c = '-';

            System.out.print(c);
        }
        System.out.println();
*/

        System.out.println();
        isr.close();
    }

    private static void bufferedReader() throws IOException {
        System.out.println("------BufferedReader-----------");
        InputStreamReader f = new InputStreamReader(new FileInputStream("data/out2.txt"), "cp1251");
        BufferedReader bf = new BufferedReader(f);
        bf.lines().forEach(System.out::println);
        bf.close();
    }

    private static void fileReader() throws IOException {
        System.out.println("-----fileReader-------");
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
        System.out.println("-----fileReader2-------");
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

}
