package com.core.filestream;

import java.io.*;

/**
 * <p>Абстрактный класс Reader. Методы для чтения synchronized.
 * <p>InputStreamReader (наследник FileReader), BufferedReader, PipedReader, StringReader, CharArrayReader,
 * abstract FilterReader (наследник PushbackReader)
 * <p></p>
 * <p>InputStreamReader. Конструктор принимает InputStream, Charset,  string charsetName.
 * <p>FileReader. Принимает File или fileName. Все методы из InputStreamReader. Кодировка utf-8.
 * <p>BufferedReader. Принимает Reader и размер буфера. Дополнительные методы
 * <p> lines() возвращает Stream<String>, readLine() возвращают строку.
 * <p>PipedReader работает аналогично PipedInputStream|PipedOutputStream</p>
 * <p>StringReader. Читает из входящей строки. Поддерживает mark|reset</p>
 * <p>CharArrayReader. Подобен StringReader. Внутренний буфер - массив типа char (длина по умолчанию 32).
 * <p>PushbackReader. Подобен StringReader. Внутренний буфер - массив типа char (длина по умолчанию 32).
 */
public class ReaderTest {
    public static void main(String[] args) {
        try {
            inputStreamReader();
            fileReader();
            fileReader2();
            bufferedReader();
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

    private static void bufferedReader() throws IOException {
        System.out.println("------BufferedReader-----------");
        InputStreamReader f = new InputStreamReader(new FileInputStream("data/out2.txt"), "cp1251");
        BufferedReader bf = new BufferedReader(f);

        //bf.lines().forEach(System.out::println);

        String line;
        int i = 1;
        while ((line = bf.readLine()) != null) {
            System.out.println((i++) + ": " + line);
        }

        bf.close();
    }


}
