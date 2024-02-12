package com.core.functional.custom;

public class TestMyPredicate {
    public static void main(String[] args) {
        MyPredicate<String> myPredicate = new MyPredicate<String>() {
            @Override
            public boolean test(String s) {
                System.out.println("s1 " + s);
                return s.length() > 2;
            }
        };

        myPredicate.and(new MyPredicate<String>() {
                    @Override
                    public boolean test(String s) {
                        System.out.println("s2 " + s);
                        return s.length() > 2;
                    }
                })
                .or(new MyPredicate<String>() {
                    @Override
                    public boolean test(String s) {
                        System.out.println("s3 " + s);
                        return s.length() > 2;
                    }
                })
                .test("wrt");
    }
}


