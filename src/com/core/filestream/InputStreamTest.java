package com.core.filestream;

import com.bean.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * <p>Интерфейс - InputStream. </p>
 * <p>Классы - FileInputStream, PipedInputStream, ByteArrayInputStream, PushbackInputStream,</p>
 * <p>ObjectInputStream (внутренние приватные BlockDataInputStream, PeekInputStream)</p>
 * <p>FilterInputStream (наследники BufferedInputStream, DataInputStream, PushbackInputStream) </p>
 * <p>
 * FileInputStream. Конструктор принимает File либо String как файл. Файл должен существовать. Методы:
 * <p>available() - кол-во доступных для считывания байт.</p>
 * <p>read() - один байт.</p>
 * <p>read(byte b[]) - массив байтов.</p>
 * <p>read(byte b[], int off, int len) - off смещение в массиве b, len сколько считать</p>
 * <p>skip(long n) - пропустить n байт относительно текущей позиции</p>
 * <p>reset() - не поддерживается</p>
 * </p>
 * <p>ByteArrayInputStream. Конструктор принимает массив байтов. Методы synchronized. Поддерживает mark/reset. </p>
 * <p>FilterInputStream. Конструктор принимает InputStream. Методы вызывают методы переданного в конструкторе класса </p>
 * <p>BufferedInputStream. Конструктор принимает InputStream, второй конструктор дополнительно int - размер внутреннего буфера.
 * Методы synchronized. Поддерживает mark/reset. </p>
 * <p> pos - текущая позиция в массиве buf[]</p>
 * <p> markpos - фиксирует pos, после чего можно вызвать reset() и вернуть pos в позицию markpos. Т.е. начать выводить символы повторно</p>
 * <p> marklimit - количество символов, считанных с потока, поселе которого markpos = -1, т.е. станет невалидным и нельзя вызывать reset()</p>
 * <p>DataInputStream. Конструктор принимает InputStream. Позволяют считывать данные примитивных типов, записанные DataOutputStream. </p>
 * <p>PushbackInputStream. Конструктор принимает InputStream. Имеет внутренний буфер (размер задается конструктором),
 * в который можно вставить символы методами unread(int), unread(bite[] buf). Последующим read будут считываться сперва эти символы. </p>
 * <p>PipedInputStream. Работает в паре с PipedOutputStream. Метод connect(PipedOutputStream src) устанавливает связь с источником</p>
 * <p>ObjectInputStream. Конструктор принимает InputStream. Считывает примитиврные типы, стоки utf, серелезуемые объекты.</p>
 */
public class InputStreamTest {
    public static void main(String[] args) {
        fileInputStream();
        try {
            fileInputStream2();
            bufferedInputStream();
            fileDataInputStream();
            fileDataInputStream2();
            pushbackInputStream();
            pipedInputStream();
            objectInputStream();
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void fileInputStream() {
        System.out.println("-----fileInputStream-------");
        FileInputStream input = null;
        try {
            input = new FileInputStream("data/out.txt");
            System.out.println("markSupported - " + input.markSupported());
            System.out.println(input.available());
            int code = input.read();
            System.out.println(code + " char = " + (char) code);
            byte[] arr = new byte[input.available()];
            int numberBytes = input.read(arr);
            System.out.println("numberBytes = " + numberBytes);
            System.out.println(Arrays.toString(arr));
            // input.close(); // wrong
            System.out.println(input.available());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void fileInputStream2() throws IOException {
        System.out.println("-----fileInputStream2-------");
        FileInputStream input = new FileInputStream("data/out.txt");

        int code = input.read();
        System.out.println(code + " char = " + (char) code);
        System.out.println("available after one read " + input.available());

        byte[] arr = new byte[input.available()];
        input.skip(2);
        int numberBytes = input.read(arr/*, 9, 5*/);
        System.out.println("numberBytes = " + numberBytes);

        int char1 = arr[3] & 0xff;
        System.out.println(arr[3] + " char = " + (char) char1);

        char[] charBuf = new String(arr, StandardCharsets.UTF_8).toCharArray();
        System.out.println(String.valueOf(charBuf));
        input.close();
    }

    private static void bufferedInputStream() throws IOException {
        System.out.println("-----bufferedInputStream-------");
        //12 - начальный размер buf[]. Первые 12 символов из потока
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream("data/out1.txt"), 12);
        System.out.println("markSupported - " + bf.markSupported());
        System.out.println("available: " + bf.available());

        int code;
        int i = 0;
        while ((code = bf.read()) > 0) {
            System.out.print((char) code);
            if (i == 8) {
                // Вывели 9 символов, и возвращаем указатель pos на третью позицию. Будет считано еще 9 символа из buf[] с 3-й позиции повторно.
                // После этого (pos = 12) перераспределиться buf[]: с 3-й позиции (markpos = 3) 9 символов переставятся в начало
                // и будет считано еще 3 символа из потока, а сбросится markpos = 0, a pos = 10.
                // Затем будет считано 3 Новых символа из buf[].
                // После первого вызова reset() и считывания с буфера всех 12 символов, размер буфера увеличится на 1
                // и станет 13.
                bf.reset();
            }
            if (i == 2) {
                // Вывели три символа и фиксируем pos = 3.
                // До тех пор, как не будет считано 13 символов после последнего вызова reset(), после чего markpos = -1.
                bf.mark(13);
            }
            i++;
        }
        System.out.println();

        bf.close();
    }

    private static void fileDataInputStream() throws IOException {
        System.out.println("-----fileDataInputStream-------");
        DataInputStream dos = new DataInputStream(new FileInputStream("data/dataout.txt"));
        try {
            String name = dos.readUTF();
            int age = dos.readInt();
            double height = dos.readDouble();
            boolean married = dos.readBoolean();
            System.out.printf("Name: %s  Age: %d  Height: %f  Married: %b",
                    name, age, height, married);
        } catch (EOFException eof) {
            System.out.println("end file");
        }
        System.out.println();
        dos.close();
    }

    private static void fileDataInputStream2() throws IOException {
        System.out.println("-----fileDataInputStream2-------");
        DataInputStream dis = new DataInputStream(new FileInputStream("data/dataout2.txt"));
        while (dis.available() > 0) {
            String k = dis.readUTF();
            System.out.print(k + " ");
        }
        System.out.println();
        dis.close();
    }

    private static void pushbackInputStream() throws IOException {
        System.out.println("-----pushbackInputStream-------");
        //
        PushbackInputStream input = new PushbackInputStream(new FileInputStream("data/out.txt"), 50);
        System.out.println("available: " + input.available());
        input.read();
        System.out.println("available after one read: " + input.available());
        input.unread(100);
        System.out.println("available after unread: " + input.available());

        while (input.available() > 0) {
            int code = input.read();
            System.out.println(code + " char = " + (char) code);
        }

        byte[] byteArray = new byte[]{'H', 'e', 'l', 'l', 'o',};
        input.unread(byteArray);
        System.out.println("available after unread2: " + input.available());
    }

    private static void pipedInputStream() throws IOException {
        System.out.println("-----pipedOutputStream-------");
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();

        in.connect(out);
        out.write(10);
        out.write(25);
        out.write(100);

/*
        int available = in.available();
        System.out.println(available);
        for (int i = 0; i < available; i++) {
            System.out.println(in.read());
        }
*/
        while (in.available() > 0) {
            System.out.println(in.read());
        }
    }

    private static void objectInputStream() throws IOException, ClassNotFoundException {
        System.out.println("-----objectInputStream-------");
        File f = new File("data/objout.txt");
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(f));

        System.out.println(ios.available());
        //byte buf[] = new byte[10000];
        //ios.readFully(buf);
        System.out.println(ios.readDouble());
        System.out.println(ios.readUTF());
        System.out.println(ios.readUTF());

        Person tom = (Person)ios.readObject();
        System.out.println(tom);

        ios.close();

    }

}
