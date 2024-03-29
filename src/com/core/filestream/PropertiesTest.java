package com.core.filestream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * <p> Класс Properties предназначен для хранения карты свойств, где и имена и значения являются экземплярами класса String.
 * <p> При сохранении файла путь долже существовать.
 * <p> Символ «=» служит по умолчанию разделителем ключа и значения в файле properties, также в этом качестве можно использовать символ «:».
 * Эти два специальных символа при записи в файл в качестве части ключа или значения получают впереди символ «\»,
 * чтобы в дальнейшем при чтении не быть воспринятым как разделитель.
 * <p> В веб-приложении поток ввода для чтения файла properties создается следующим образом:
 * <pre>this.getClass().getClassLoader().getResourceAsStream("data//base.properties")</pre>
 */
public class PropertiesTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
            props.setProperty("db.url", "jdbc:mysql://127.0.0.1:3306/testphones");
            props.setProperty("user", "root");
            props.setProperty("password", "pass");
            props.setProperty("poolsize", "5");
            props.store(new FileWriter("data/base.properties"), "No Comment’s");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            props.load(new FileReader("data\\base.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbUrl = props.getProperty("db.url");
        // following two names are missing in the file
        String maxIdle = props.getProperty("maxIdle"); // maxIdle = null value "20" will be assigned to the key if it is not found in the file
        String maxActive = props.getProperty("maxActive", "20");
        System.out.println("dbUrl: " + dbUrl);
        System.out.println("maxIdle: " + maxIdle );
        System.out.println("maxActive: " + maxActive);

    }
}
