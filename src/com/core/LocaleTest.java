package com.core;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleTest {
    public static void main(String[] args) {
        Locale current = Locale.getDefault();

        System.out.println(current.getCountry()); // region code
        System.out.println(current.getDisplayCountry()); // region name
        System.out.println(current.getLanguage()); // region language code
        System.out.println(current.getDisplayLanguage()); // region language name

        ResourceBundle ru = ResourceBundle.getBundle("text", current);
        System.out.println(ru.getString("group1.key10"));

        ResourceBundle canadaFr = ResourceBundle.getBundle("text", Locale.CANADA_FRENCH);
        System.out.println(canadaFr.getString("group1.key10"));

        try {
            System.out.println(canadaFr.getString("group1.key1000"));
        } catch (MissingResourceException ex) {
            System.out.println("group1.key1000 not found");
        }

        ResourceBundle database = ResourceBundle.getBundle("database");
        System.out.println(database.getString("driver.name"));

    }
}
