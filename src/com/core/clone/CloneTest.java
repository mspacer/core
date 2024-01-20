package com.core.clone;

import java.util.ArrayList;

/*
• интерфейс Cloneable обязателен для клонируемго объекта, иначе CloneNotSupportedException.
    Интерфейс не содержит методов, относится к помеченным (tagged) интерфейсам,
    а его реализация гарантирует, что метод clone() класса Object возвратит точную
    копию вызвавшего его объекта с воспроизведением значений всех его полей
• "неглубокое" клонирование эффективно только в случае, когда поля клонируемого объекта
    представляют собой значения базовых типов и их оболочек или неизменяемых (immutable) объектных типов.
    Если же поле клонируемого типа является изменяемым объектным типом, то для корректного клонирования требуется
    переопределение clone для таких объектов
• следует отказаться от использования объявлений final для полей объектных типов по причине невозможности
    изменения их значений при реализации клонирования
*/

public class CloneTest {
    public static void main(String[] args) {
        Abiturient abiturient = new Abiturient(1);
        abiturient.setAddress(new Address("Kiev"));
        ArrayList<Byte> list = new ArrayList<>();
        list.add((byte) 1);
        list.add((byte) 2);
        list.add((byte) 3);
        abiturient.setList(list);
        ArrayList<Subject> subjects = new ArrayList<>();
        abiturient.setSubjects(subjects);
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("History"));

        Abiturient abiturient1 = abiturient.clone();
        abiturient1.getAddress().setCity("New York");

        // финальный параметр. Клонирование невозможно, поэтому будет изменен и в начальном объекте
        abiturient1.getAddress().country.setName("RUS");

        list = abiturient1.getList();
        list.remove(0);
        list.add((byte)9);

        abiturient1.setOrder(123);
        abiturient1.setId(10);

        abiturient1.getSubjects().add(new Subject("Chemistry"));
        abiturient1.getSubjects().get(0).setName("Geogaphy");

        System.out.println(abiturient);
        System.out.println(abiturient1);

    }
}
