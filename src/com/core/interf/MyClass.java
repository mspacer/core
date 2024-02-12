package com.core.interf;

public class MyClass implements MyInterface  {
    @Override
    public int myBirthday() {
        return 1973;
    }

    @Override
    public <A, B extends MyInterface2> A testParam(A a, B b) {
        System.out.println(b.name());
        return a;
    }

    public String getComment() {
        return new MyInnerClass().comment();
    }

    public MyEnum getInnerEnum() {
        return MyEnum.ONE;
    }

    class MyInnerClass implements MyInterface.MyInnerInterface {

        @Override
        public String comment() {
            return "from Inner";
        }
    }
}


