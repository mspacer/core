package com.core.collection.list;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ArraysTest {
    public static void main(String[] args) {
        Object[] rowData = Arrays.asList(
                "№",
                "Регистрационный номер выпуска облигаций в рамках программы",
                "Дата регистрации выпуска",
                "Состояние выпуска",
                "Количество облигаций в выпуске в соответствии с заявлением на государственную регистрацию с учетом изменений, шт.",
                "Номинальная стоимость одной облигации выпуска",
                "Наименование валюты номинала",
                "Курс валюты номинала выпуска к валюте программы",
                "Зарегистрированный объем выпуска в валюте программы",
                "Количество размещенных облигаций в соответствии с уведомлением об итогах, шт.",
                "Объем выпуска по номинальной стоимости  по итогам размещения",
                "Объем выпуска в валюте программы, учитываемый в расчете"
        ).toArray();
        print(rowData);

        rowData = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11").toArray();

        print(rowData);
        print2(rowData);
        print2(Arrays.copyOfRange(rowData, 1, 10));
        print2(rowData, rowData);
    }

    private static void print(Object[] rowData) {
        System.out.println("rowData: " +  Arrays.toString(rowData));
    }

    private static void print2(final Object... data) {
        for (Object obj : data) {
            System.out.print(obj + "; ");
        }
        System.out.println();
    }

}
