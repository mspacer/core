package com.core.reflection;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestReflection2 {

    public static void main(String[] args) throws Exception {
        commonClassInfo();
        modifiersMethod();
        packageInfo();
        superClassInfo();
        interfacesInfo();
        constructorsInfo();
        fieldsInfo("com.core.reflection.Animal");
        fieldsInfo("com.core.reflection.Goat");
        methodsInfo("com.core.reflection.Animal");
        methodsInfo("com.core.reflection.Goat");
    }

    private static void constructorsInfo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("--constructorsInfo--");
        Class<?> goatClass = Class.forName("com.core.reflection.Goat");
        Constructor<?>[] constructors = goatClass.getDeclaredConstructors();

        System.out.println(constructors.length);
        System.out.println(Arrays.asList(constructors));

        Constructor<?> constructor =  goatClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Goat instance = (Goat)constructor.newInstance("");
        System.out.println(instance.eats());

        System.out.println("----------------");
    }

    private static void fieldsInfo(String classForName) throws ClassNotFoundException {
        System.out.println("--fieldsInfo " + classForName + " --");
        Class<?> animalClass = Class.forName(classForName);
        Field[] fields = animalClass.getDeclaredFields();
        List<String> actualFields = getFieldNames(fields);

        System.out.println(actualFields);
        System.out.println("----------------");
    }

    private static void methodsInfo(String classForName) throws ClassNotFoundException {
        System.out.println("--methodsInfo " + classForName + " --");
        Class<?> animalClass = Class.forName(classForName);
        Method[] methods = animalClass.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);
        System.out.println(actualMethods);
        System.out.println("----------------");
    }

    private static void interfacesInfo() throws ClassNotFoundException {
        System.out.println("--interfacesInfo--");
        Class<?> goatClass = Class.forName("com.core.reflection.Goat");
        Class<?> animalClass = Class.forName("com.core.reflection.Animal");

        Class<?>[] goatInterfaces = goatClass.getInterfaces();
        Class<?>[] animalInterfaces = animalClass.getInterfaces();

        System.out.println(Arrays.asList(goatInterfaces));
        System.out.println(goatInterfaces[0].getSimpleName());
        System.out.println(Arrays.asList(animalInterfaces));
        System.out.println(animalInterfaces[0].getSimpleName());
        System.out.println("----------------");
    }

    private static void superClassInfo() {
        System.out.println("--superClassInfo--");
        Goat goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Class<?> goatSuperClass = goatClass.getSuperclass();
        System.out.println(goatSuperClass.getSimpleName());

        String str = "any string";
        System.out.println(str.getClass().getSuperclass().getSimpleName());
        System.out.println("----------------");
    }

    private static void packageInfo() {
        System.out.println("--packageInfo--");
        Goat goat = new Goat();
        Class<?> goatClass = goat.getClass();
        Package pkg = goatClass.getPackage();
        System.out.println(pkg.getName());
        System.out.println("----------------");
    }

    private static void modifiersMethod() throws ClassNotFoundException, NoSuchMethodException {
        System.out.println("--modifiersMethod--");
        Class<?> goatClass = Class.forName("com.core.reflection.Goat");
        Class<?> animalClass = Class.forName("com.core.reflection.Animal");

        int goatMods = goatClass.getModifiers();
        int animalMods = animalClass.getModifiers();
        System.out.println(goatMods + "/" + animalMods);

        System.out.println(Modifier.isFinal(goatMods));
        System.out.println(Modifier.isAbstract(animalMods));
        System.out.println(Modifier.isPublic(animalMods));
        System.out.println(Modifier.isPublic(TestReflection2.class.getModifiers()));

        System.out.println("Methods");
        System.out.println(Modifier.isProtected(goatClass.getDeclaredMethod("getSound").getModifiers()));

        System.out.println("----------------");
    }

    private static void commonClassInfo() throws ClassNotFoundException {
        System.out.println("--commonClassInfo--");
        Object goat = new Goat();
        Class<?> clazz = goat.getClass();

        System.out.println("getSimpleName " + clazz.getSimpleName());
        System.out.println("getName " + clazz.getName());
        System.out.println("getCanonicalName " + clazz.getCanonicalName());

        clazz = Class.forName("com.core.reflection.Goat");
        System.out.println("getSimpleName " + clazz.getSimpleName());
        System.out.println("getName " + clazz.getName());
        System.out.println("getCanonicalName " + clazz.getCanonicalName());
        System.out.println("getGenericSuperclass " + clazz.getGenericSuperclass());

        System.out.println("----------------");
    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> methodNames = new ArrayList<>();
        for (Field field : fields)
            methodNames.add(field.getName());
        return methodNames;
    }

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }
}

interface Eating {
    String eats();
}

interface Locomotion {
    String getLocomotion();
}

abstract class Animal implements Eating {

    public static String CATEGORY = "domestic";
    private String name;

    protected abstract String getSound();

    // constructor, standard getters and setters omitted
}

final class Goat extends Animal implements Locomotion {

    public Goat() {
    }

    private Goat(String s) {
    }

    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    public String eats() {
        return "grass";
    }

    // constructor omitted
}