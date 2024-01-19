package com.bean;

public class Task<T extends Number> {
    private String name;
    private T mark;

    public Task(T mark, String name) {
        this.name = name;
        Task.this.mark = mark;
    }

    public T getMark() {
        return mark;
    }

    public boolean equalsToMark(Task<T> task) {
        return roundMark() == task.roundMark();
    }

    /* экземпляры класса Task, инициализированные любым допустимым типом */
    public boolean equalsToMarkAny(Task<?> task) {
        return roundMark() == task.roundMark();
    }

    private int roundMark() {
        return Math.round(mark.floatValue());
    }
}
