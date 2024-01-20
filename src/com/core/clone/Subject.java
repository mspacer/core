package com.core.clone;

public class Subject implements Cloneable {
    String name;

    public Subject(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Subject clone() throws CloneNotSupportedException {
        return (Subject)super.clone();
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }
}
