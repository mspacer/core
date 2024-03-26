package com.core.filestream;

import java.io.*;

/**
 * <p>абстрактный класс Writer. Дополнительные методы append: (char), (CharSequence). Методы для записи synchronized.</p>
 * <p>OutputStreamWriter (наследник FileWriter), BufferedWriter, abstract FilterWriter, PrintWriter, PipedWriter, StringWriter,
 * CharArrayWriter</p>
 * <p></p>
 * <p>OutputStreamWriter. Конструктор принимает OutputStream и charset (строка/класс).
 * Является мостом между строковым потоком и потоком байтов.</p>
 * <p>FileWriter удобная оболочка для File. UTF-8 дефолтная кодировка.
 * <p>BufferedWriter. Принимает Witer и размер буфера. Дополнительный метод newLine()</p>
 * <p></p>
 * <p>PrintWriter. Принимает FileOutputStream, File, String filename, encoding</p>
 * <p>дополнительные методы print, println для примитивных типов </p>
 * <p>PrintStream format(String format, Object ... args) </p>
 * <p>PrintStream printf(...) (вызывает format)</p>
 * <p></p>
 * <p>PipedWriter работает аналогично PipedInputStream|PipedOutputStream</p>
 * <p>StringWriter. Создает внутренний буфер типа StringBuffer, в который происходит запись. Метод getBuffer() его возвращает</p>
 * <p>CharArrayWriter. Подобен StringWriter. Внутренний буфер - массив типа char (длина по умолчанию 32).
 * Метод toCharArray() его возвращает</p>
 */
public class WriterTest {
    public static void main(String[] args) {
        try {
            outputStreamWriter();
            bufferedWriter();
            printWriter();
            stringWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputStreamWriter() throws IOException {
        FileOutputStream fis = new FileOutputStream("data/outStrmW.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fis, "cp1251");

        String s = "оутпут стрим врайтер";
        char[] buf = {'A', 'S', 'C', 'I', 'I', ' ', 'с', 'и', 'м', 'в', 'о', 'л', ' ', '1', '&', ',', '-'};

        osw.write(s, 0, s.length());
        osw.write(32);// код пробела
        osw.write(111);
        osw.write(32);
        osw.write(buf);
        osw.write(32);
        osw.write(buf, 0, buf.length);

        osw.close();

        FileWriter fw = new FileWriter("data/outStrmW.txt", true);
        fw.write('\n');
        fw.write("Из FileWriter");

        fw.close();
    }

    private static void bufferedWriter() throws IOException {
        FileOutputStream fis = new FileOutputStream("data/bufStrmW.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fis, "cp1251");
        BufferedWriter bw = new BufferedWriter(osw);

        String s = "оутпут стрим врайтер";
        char[] buf = {'A', 'S', 'C', 'I', 'I', ' ', 'с', 'и', 'м', 'в', 'о', 'л', ' ', '1', '&', ',', '-'};

        bw.write(s);
        bw.append('?').append(' ');

        bw.newLine();
        StringBuilder sb = new StringBuilder();
        sb.append("стринг билдер");

        bw.append(sb); //writer.append(CharSequence)

        bw.close();

    }

    private static void printWriter() throws IOException {
        PrintStream prs = new PrintStream(new FileOutputStream("data/prStrmW.txt"), true, "cp1251");

        prs.print(10);
        prs.println(11);
        prs.println("строка");
        prs.format("This is a %s %d program", "моя", 1)
        .format(" на java.")
        .printf(" Java %.2g%n", 1.8);

        prs.close();
    }

    private static void stringWriter() throws IOException {
        StringWriter sw = new StringWriter();
        sw.write("Стринг врайтер");
        sw.append('\n');
        sw.append("Новая строка.");
        System.out.println(sw.getBuffer());
        sw.close();
    }

}
