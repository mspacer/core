package com.core.oop;

/**
 • При создании класса первыми выполняются блоки инициализации static {} и {}. Затем вызывается конструктор
 • Первым инициализируеся родительский класс
 • Всегда вызываются переопределенные (override) в дочернем классе методы. Родительские только через super.
 • final static примитивы создаются сразу. Оболочки примитивав нет.
 • в дочернем классе @Override метод может стать final, но не static
 • использованием super можно обратиться только к ближайшему суперклассу, т.е. «перескочить» через суперкласс, чтобы обратиться
    к его суперклассу, невозможно. (т.е. через super нельзя вызывать изначальную версию переопределенного метода из супер-супер класса.)
 • При переопределении можно пользоватья Методом подстановки, когда используется дочерний клас как возвращаемое значение
 •
 •
 */

public class TestInhered {
    public static void main(String[] args) {
        testExtends();
        //System.out.println(Dumber.instance.id);
        //System.out.println(Dumber.B);

    }

    private static void testExtends() {
        System.out.println("testExtends");

        //new Dumb().getId();
        Dumber dumber = new Dumber(1);
        dumber.getId();
        dumber.getISuperId();

        System.out.println("________________");
    }

}


class Dumb {
    {
        System.out.println("bl1");
        this.id = 6;
    }
    int id = 7;
    {
        System.out.println("bl2");
        this.id = 8;
    }

    Dumb() {
        System.out.printf("constructor Dumb id=%d\n", id);
        id = getId(); // ~ this.getId(); // ~ Dumb.this.getId(); при new Dumber() вызывается override метод из Dumber
        System.out.println("constructor Dumb id=" + id);

    }

    int getId() { // 1
        System.out.printf("getId() of Dumb id=%d\n", id);
        return id;
    }

    {
        System.out.println("bl3");
        this.id = 9;
    }

    static {
        System.out.println("static bl Dumb");
    }

}

class Dumber extends Dumb {
    public static final Dumber instance = new Dumber(2);
    public static final int A = /*new Integer(99);*/5;
    public static int B = 7;

    int id = 10;
    {
        System.out.printf("Dumber bl1 id=%d\n", id);
        System.out.printf("Dumber bl1 B=%d\n", B);
        this.id = 11;
    }
    static {
        System.out.printf("Dumber static bl2 B=%d\n", B);
        B = 9;
    }
    Dumber(int i) {
        System.out.printf("constructor Dumber %d\n", i);

        System.out.printf("constructor Dumber A + B = %d\n", A + B);

        id = this.getId();
        System.out.println("constructor Dumber id=" + id);

        super.getId();
    }
    @Override
    final int getId() { // 2
        System.out.printf("getId() of Dumber id=%d\n", id);
        return id;
    }

    int getISuperId() {
        System.out.print("getISuperId() ");
        return super.getId();
    }

}

class Ia {
    protected Number getNum() {
        System.out.println("Ia getNum()");
        return 1;
    }
}

class Ib extends Ia{
    // Метод подстановки. Используется дочерний клас как возвращаемое значение
    @Override
    protected Integer getNum() {
        System.out.print("Ib getNum() / ");
        super.getNum();
        return 2;
    }
}