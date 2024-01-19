package com.bean;

public class Department {
    {
        System.out.println("logic (1) id=" + this.id);
    }
    static {
        System.out.println("static logic");
        Base.nameClass2();
    }

    private int id = 42;

    public Department(int id) {
        this.id = id;
        System.out.println("constructor id=" + id);
    }

    public int getId() {
        return id;
    }

    { /* not very good location of the logical block */
        System.out.println("logic (2) id=" + id);
    }
}
