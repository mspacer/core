package com.core.annot;

import com.core.annot.an.Test;
import com.core.annot.bean.Sample1;
import com.core.annot.bean.Sample2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**

 - пример обработки аннотаци при реализазии Proxy - BankingMain
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Sample1 sample1 = new Sample1();
        run(sample1);

        Sample2 sample2 = new Sample2();
        run(sample2);
    }

    public static void run(Object obj) {
        System.out.println("invoke " + obj.getClass().getName());
        int tests = 0;
        int passed = 0;
        for (Method m : obj.getClass().getDeclaredMethods()) {
            //System.out.println("method: " + m.getName());
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(obj);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                } catch (IllegalAccessException exc) {
                    System.out.println("Invalid @Test: " + m);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }
}
