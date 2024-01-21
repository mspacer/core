package com.core.clases;

public class LinkPackageClass {
    public PackageClass getPackageClass() {
        PackageClass packageClass =  new PackageClass();
        System.out.println("create " + packageClass.toString());
        return packageClass;
    }
}
