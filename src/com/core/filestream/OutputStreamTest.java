package com.core.filestream;

import com.bean.Person;

import java.io.*;

public class OutputStreamTest {
    public static void main(String[] args) {
        fileOutPutStream();
        try {
            printStream();
            dataOutputStream();
            dataOutputStream2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileOutPutStream() {
        File file = new File("data", "out.txt" );
        try (FileOutputStream output = new FileOutputStream(file/*"data/out2.txt"*/, false)) {
            output.write(48);
            byte[] value = {65, 67, 100, 1, 22, 32, 117 };
            output.write(value);

            output.write(' ');

            char[] buf1 = {'A', 'S', 'C', 'I', 'I', ' ','s', 'i', 'm', 'b', 'o', 'l', ' ', '1', '&', ',', '-'};
            for (char c: buf1) {
                output.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Person tom = new Person("Tom", 34, 1.68, false);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
        dos.writeUTF(tom.name);
        dos.writeInt(tom.age);
        dos.writeDouble(tom.height);
        dos.writeBoolean(tom.married);
        dos.close();
    }

    private static void dataOutputStream2() throws IOException {
        File f = new File("data/dataout2.txt");
        f.delete();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
        dos.writeUTF("Строка, записанная DataOutputStream");
        dos.close();
    }

}
