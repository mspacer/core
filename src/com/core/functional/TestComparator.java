package com.core.functional;

import java.util.Comparator;
import java.util.Objects;

import static java.lang.System.out;

/**
 - Метод boolean equals(Object obj) рекомендуется переопределять, если экземпляр класса будет использоваться для хранения информации.
    Это необходимо для исключения противоречивой ситуации, когда для двух объектов метод compare() возвращает 0,
    т.е. сообщает об их эквивалентности, в то же время метод equals() для этих же объектов возвращает false, так как данный метод
    не был никем определен и была использована его версия из класса Object
    Кроме того, наличие метода equals() обеспечивает корректную работу метода семантического поиска и проверки на идентичность contains(Object o),
    определенного в интерфейсе java.util.Collection, а следовательно, реализованного в любой коллекции
 */
public class TestComparator {
    public static void main(String[] args) {
        Comparator<MyComparableClass> comparator = (o1, o2) -> o1.getTitle().length() - o2.getTitle().length();
        Comparator<MyComparableClass> comparator2 = Comparator.comparingInt(MyComparableClass::hashCode);

        boolean equals = comparator.equals(comparator2);
        out.println(equals);

        MyComparableClass myComparableClass1 = new MyComparableClass("text1");
        MyComparableClass myComparableClass2 = new MyComparableClass("text1");
        out.println(myComparableClass1.hashCode() + "/" + myComparableClass2.hashCode());

        out.println(comparator2.compare(myComparableClass1, myComparableClass2));

    }
}

class MyComparableClass{
    private String title;

    public MyComparableClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyComparableClass that = (MyComparableClass) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "MySupplier{" +
                "title='" + title + '\'' +
                '}';
    }
}