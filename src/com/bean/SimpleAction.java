package com.bean;

import java.util.Objects;

public class SimpleAction {
    public <T extends Number> SimpleAction (T course) {
    }

    public <T> SimpleAction() {
    }

    public <T extends Integer> float calculateMark(T course) {
        return course.intValue() + 2 ;
    }

    public <T> boolean printReport(T course) {
        return Objects.isNull(course);
    }

    public <T> void check() {
    }
}
