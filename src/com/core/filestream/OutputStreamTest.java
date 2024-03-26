package com.core.filestream;

import com.bean.Person;

import java.io.*;

/**
 * <p>abstract class OutputStream. </p>
 * <p>Классы - FileOutputStream, PipedOutputStream, ByteArrayOutputStream, ObjectOutputStream,</p>
 * <p>FilterOutputStream (наследники PrintStream, BufferedOutputStream, DataOutputStream) </p>
 <p>
 * FileOutputStream. Конструктор принимает File либо String как файл, append - новый или дополнять.
 * Путь должен существовать. Методы:
 * <p>write() - один байт.</p>
 * <p>write(int) - один байт.</p>
 * <p>write(byte b[]) - массив байтов.</p>
 * <p>write(byte b[], int off, int len) - off смещение в массиве b, len сколько считать</p>
 * <p>getChannel() - создает канал FileChannel (cm nio)  </p>
 * <p></p>
 * </p>
 * <p>ByteArrayOutputStream. Создает внутренний массив байтов переданным в конструктоа размером (32 по умолчарнию).
 * Методы synchronized. Поддерживает увеличение. </p>
 * <p>writeTo(OutputStream out) - запись в переданный поток.</p>
 * <p>toByteArray()[] - возвращает копию внутреннего массива.</p>
 * <p>reset() - count = 0. Предыдущее заполнение теряется.</p>
 * <p></p>
 * <p>PipedOutputStream. см PipedInputStream</p>
 * <p></p>
 * <p>PrintStream. Запись любых простых типов, включая строки в заданной кодировке. Фактически запись осуществляется в
 * текстовом формате BufferedWriter</p>
 * <p>DataOutputStream. Конструктор принимает OutputStream. Запись примитивных типов, строки в формате UTF. </p>
 * <p></p>
 * <p>ObjectOutputStream. Конструктор принимает OutputStream. Записывает примитиврные типы, стоки utf, серелезуемые объекты.</p>
 */
public class OutputStreamTest {
    public static void main(String[] args) {
        fileOutPutStream();
        try {
            byteArrayOutputStream();
            printStream();
            dataOutputStream();
            dataOutputStream2();
            objectOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileOutPutStream() {
        File file = new File("data", "out.txt" );
        try (FileOutputStream output = new FileOutputStream(file, false)) {
            output.write(48);
            byte[] value = {65, 67, 100, 1, 22, 32, 117 };
            output.write(value);

            output.write(' ');

            char[] buf1 = {'A', 'S', 'C', 'I', 'I', ' ','s', 'i', 'm', 'b', 'o', 'l', ' ', '1', '&', ',', '-'};
            for (char c: buf1) {
                output.write(c);
            }

            output.write(' ');
            output.write(127);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void byteArrayOutputStream() throws IOException {
        System.out.println("-----byteArrayOutputStream-------");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
        FileOutputStream output = new FileOutputStream("data/byteArOut.txt", false);
        byte[] bs = {65, 66, 67, 68, 69};

        baos.write(bs);
        //baos.reset();
        baos.write(70);
        baos.write(71);

        for (byte b : baos.toByteArray()) {
            System.out.print(b);
        }

        baos.writeTo(output);

        baos.close();
        output.close();
    }

    private static void printStream() throws FileNotFoundException, UnsupportedEncodingException {
        PrintStream printStream = new PrintStream("data/out2.txt", "cp1251");
        printStream.print("это строка");
        printStream.print("это вторая без перехода строка");
        printStream.println();
        printStream.println("это третья строка");
        char[] buf1 = {'И', 'з', ' ', 'м', 'а', 'с', 'с', 'и', 'в', 'а', ' ', 'c', 'h', 'a', 'r'};
        printStream.println(buf1);
        printStream.println(buf1.length);
        printStream.println(true);

        printStream.close();
    }

    private static void dataOutputStream() throws IOException {
        File f = new File("data/dataout.txt");
        f.delete();
        Person tom = new Person("Tom", 34, 1.68, false, "");
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
        dos.writeUTF(tom.getName());
        dos.writeInt(tom.getAge());
        dos.writeDouble(tom.getHeight());
        dos.writeBoolean(tom.isMarried());
        dos.close();
    }

    private static void dataOutputStream2() throws IOException {
        File f = new File("data/dataout2.txt");
        f.delete();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
        dos.writeUTF("Строка, записанная DataOutputStream");
        dos.close();
    }

    private static void objectOutputStream() throws IOException {
        File f = new File("data/objout.txt");
        f.delete();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

        //oos.writeBytes("просто строка");
        oos.writeDouble(3.1415);
        oos.writeUTF("utf строка");
        oos.writeUTF("utf вторая строка");

        Person tom = new Person("Tom", 34, 1.68, false, "");
        oos.writeObject(tom);

        oos.close();
    }

}
