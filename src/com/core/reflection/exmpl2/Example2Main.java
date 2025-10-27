package com.core.reflection.exmpl2;

import com.bean.Person;
import com.core.reflection.exmpl1.Configuration;
import com.core.reflection.exmpl1.MethodMetadata;
import com.core.reflection.exmpl1.pak.PublicConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Example2Main {

    public static void main(String[] args) {
        try {
            System.out.println(Person.class.getDeclaredConstructor().newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
