package com.core.inner;

public class Simple {

    public static int ID = 1;
    private int var1 = 101;
    private SimpleInnerPublic simpleInnerPublic = new SimpleInnerPublic(102);
    private SimpleInnerPrivate simpleInnerPrivate = new SimpleInnerPrivate();

    public static void staticMethod() {
        //new SimpleInnerPublic();
    }

    public SimpleInnerPrivate getSimpleInnerPrivate() {
        return simpleInnerPrivate;
    }

    public int getInnerVar() {
        return simpleInnerPublic.var1;
    }

    public class SimpleInnerPublic {
        public static final int ID = 777;
        private int var1 = -1;

        public SimpleInnerPublic(int var) {
            Simple.this.var1 = var;
        }

        protected int getVar() {
            return Simple.this.var1;
        }

        protected int getLocalVar() {
            return var1;
        }

        @Override
        public String toString() {
            return "SimpleInnerPublic{}";
        }
    }

    private class SimpleInnerPrivate {
        public static final int ID = 777;

        public void comment() {
            System.out.println("!!!");
        }

        @Override
        public String toString() {
            return "SimpleInnerPrivate{}";
        }
    }

    public enum InnerEnum {
        INNER_ENUM("внутренний энум");
        String value;

        InnerEnum(String value) {
            this.value = value;
        }
    }
}
