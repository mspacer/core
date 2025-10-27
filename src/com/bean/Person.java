package com.bean;

import java.io.Serializable;

public class Person implements Serializable {
    public static String TMP = "tmp";
    private String name = "root"; //default
    private int age;
    private double height;
    private boolean married;
    transient private String password = "123456"; // default

    public Person() {
    }

    public Person(String n, int a, double h, boolean m, String password) {
        this.name = n;
        this.height = h;
        this.age = a;
        this.married = m;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", married=" + married +
                ", password=" + password +
                '}';
    }
}
