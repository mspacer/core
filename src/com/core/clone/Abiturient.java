package com.core.clone;

import java.util.ArrayList;

public class Abiturient extends Key
        implements Cloneable {
    private int order = 7;
    private ArrayList<Byte> list = new ArrayList<>();
    private ArrayList<Subject> subjects = new ArrayList<>();

    public Abiturient(int id) {
        super(id);
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ArrayList<Byte> getList() {
        return list;
    }

    public void setList(ArrayList<Byte> list) {
        this.list = list;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public Abiturient clone() {
        Abiturient copy = null;
        try {
            copy = (Abiturient)super.clone();
            copy.list = (ArrayList<Byte>) list.clone();

            //copy.subjects = (ArrayList<Subject>) subjects.clone();
            copy.subjects = new ArrayList<>();
            for (Subject subject: subjects) {
                copy.subjects.add(subject.clone());
            }

            // можно не реализавывать Cloneable в родительском объекте
            copy.setAddress(copy.getAddress().clone());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Abiturient{" +
                "order=" + order +
                ", list=" + list +
                ", subjects=" + subjects +
                '}';
    }
}
