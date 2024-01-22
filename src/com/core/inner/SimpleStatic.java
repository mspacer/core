package com.core.inner;

import java.util.Comparator;

public class SimpleStatic {
    private static String ownerStatic = "Owner static";

    private int var1 = 201;
    //private SubNested nested = new SubNested(this);
    private int group;;
    private String name;
    private float mark;

    public SimpleStatic() {
    }

    public SimpleStatic(int group, String name, float mark) {
        this.group = group;;
        this.name = name;
        this.mark = mark;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public float getMark() {
        return mark;
    }

    private static void createNestedStatic() {
        new SubNested();
    }

    private void createNested() {
        new SubNested();
    }

    public static class SubNested {
        public static final int ID = 888;
        private int var1 = -2;
        private final SimpleStatic outer;

        public SubNested() {
            outer = null;
        }

        public SubNested(SimpleStatic outer) {
            this.outer = outer;
            new SimpleStatic().group = 10;
        }

        protected int getVar() {
            return outer.var1;
        }

        protected int getLocalVar() {
            return var1;
        }

        protected String getStaticOwnerVar() {
            return ownerStatic;
        }

        protected static String getStaticOwnerVar2() {
            return ownerStatic;
        }

        @Override
        public String toString() {
            return "SubNested{}";
        }
    }

    // nested classes
    public static class GroupComparator implements Comparator<SimpleStatic> {
        @Override
        public int compare(SimpleStatic o1, SimpleStatic o2) {
            return o1.group - o2.group;
        }
    }
    public static class NameComparator implements Comparator<SimpleStatic> {
        @Override
        public int compare(SimpleStatic o1, SimpleStatic o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    public static class MarkComparator implements Comparator<SimpleStatic> {
        @Override
        public int compare(SimpleStatic o1, SimpleStatic o2) {
            return Float.compare(o2.mark, o1.mark);
        }
    }
}
