package com.core.filestream;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * <p>Объект класса java.util.Scanner принимает форматированный объект или ввод из потока и преобразует его в двоичное представление.
 * При вводе могут использоваться данные из консоли, файла, строки или любого другого источника, реализующего интерфейсы
 * Readable, InputStream или ReadableByteChannel.
 * <p>Scanner предлагает наиболее удобный и полный интерфейс для извлечения информации практически из любых источников.</p>
 *<p>Объект класса Scanner определяет границы лексемы, основываясь на наборе разделителей. Можно задавать разделители с помощью метода
 * useDelimiter(Pattern pattern) или useDelimiter(String regex), где pattern и regex содержит набор разделителей в виде регулярного выражения.
 * <p>Применение метода useLocale(Locale loc) позволяет задавать правила чтения информации, принятые в заданной стране или регионе.
 */

public class ScannerTest {
    public static void main(String[] args) {
        scanConsole();
        scanfile();
        scanString();
        search();

        System.out.println();
    }

    private static void scanConsole() {
        System.out.println("-------scanConsole----------");
        Scanner console = new Scanner(System.in);
        //String str1 = console.next();
        String str2 = console.nextLine();
        if(console.hasNextInt()) {
            int number = console.nextInt();
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter number: ");
            System.out.println("entered: " + Integer.valueOf(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanfile() {
        System.out.println("-------scanfile----------");
        String filename = "data\\scan.txt";
        try(Scanner scan = new Scanner(new FileReader(filename))) {
            while (scan.hasNext()) {
                if (scan.hasNextInt()) {
                    System.out.println(scan.nextInt() + " :int");
                } else if (scan.hasNextBoolean()) {
                    System.out.println(scan.nextBoolean() + " :boolean");
                } else if (scan.hasNextDouble()) {
                    System.out.println(scan.nextDouble() + " :double");
                } else {
                    System.out.println(scan.next() + " :String");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void scanString() {
        System.out.println("-------scanString----------");
        double sum = 0.0;
        String numbersStr = "1,3;2,0; 8,5; 4.8;9,0; 1; 10;";
        Scanner scan = new Scanner(numbersStr)
                .useLocale(Locale.FRANCE) // change to Locale.US
                .useDelimiter(";\\s*");
        while (scan.hasNext()) {
            if (scan.hasNextDouble()) {
                sum += scan.nextDouble();
            } else {
                System.out.println(scan.next());
            }
        }
        System.out.printf("Sum = " + sum);
        System.out.println();
        scan.close();
    }

    private static void search() {
        System.out.println("-------search----------");
        String filename = "data\\scan.txt";
        try(Scanner scan = new Scanner(new FileReader(filename))) {
            String subStr = scan.findWithinHorizon("true", 0);
            System.out.println(subStr);

            System.out.println(scan.nextLine());
            String text = scan.findInLine("тест"/*"text"*/); //"тест" - ok,"text" - null
            System.out.println("find: " + text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
