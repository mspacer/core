package com.core.functional.custom;

public class SomeFnClass {
    private String inner = "";

    public SomeFnClass(String inner) {
        this.inner = inner;
    }

    public SomeFnClass of(String s) {
        inner += " " + s;
        return this;
    }

    public String getInner() {
        return inner;
    }
}
