package com.core.reflection.exmpl1;

import com.core.reflection.exmpl1.pak.PublicConfig;

import java.lang.reflect.Field;

public class Example1Main {

    public static void main(String[] args) {
        Configuration configuration = PublicConfig.publicConfiguration();
        try {
            Object root = Class.forName("com.core.reflection.exmpl1.pak.PackageOnly$PackageConfiguration")
                    .cast(configuration);
            Field factoryMethodMetadata = root.getClass().getDeclaredField("factoryMethodMetadata");
            factoryMethodMetadata.setAccessible(true);
            MethodMetadata methodDate = (MethodMetadata) factoryMethodMetadata.get(root);
            System.out.println(methodDate.methodName());
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
