package com.core.clases;

import java.util.Scanner;

public class InputTest {
    public static void main(String... args) {
        /* int x;
        try {
            x = System.in.read();
            char c = (char)x;
            System.out.println("Character Code: " + c + " =" + x);
        }
        catch (IOException e) {
            System.err.println("i\\o error " + e);
        }
*/

        System.out.println("Enter name and press <Enter> & number and press <Enter>:");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine(); //scan.next();
        System.out.println("hello, " + name);
        int num = scan.nextInt();
        System.out.println("number= " + num);
        scan.close();
    }
}
