package com.core.reflection.exmpl1.pak;

import com.core.reflection.exmpl1.Configuration;

public class PublicConfig {

    public static Configuration publicConfiguration() {
        PublicMethodMetadata methodMetadata = new PublicMethodMetadata("commonMethod");
        return new PackageOnly(methodMetadata).getDefaultConfig();
    }

    public static Configuration tmpConfiguration() {
        return new PackageOnly.TmpConfiguration();
    }
}
