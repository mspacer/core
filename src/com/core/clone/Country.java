package com.core.clone;

public class Country implements Cloneable{
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Country clone() throws CloneNotSupportedException {
        return (Country)super.clone();
    }

    @Override
    public String toString() {
        return name;
    }
}
