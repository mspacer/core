package com.core.reflection.exmpl1.pak;

import com.core.reflection.exmpl1.Configuration;
import com.core.reflection.exmpl1.MethodMetadata;
import com.core.reflection.exmpl1.RootConfiguration;

class PackageOnly {

    private Configuration defaultConfig;

    PackageOnly(MethodMetadata defaultMetadata) {
        defaultConfig = new PackageConfiguration(defaultMetadata);
    }

    public Configuration getDefaultConfig() {
        return defaultConfig;
    }

    private static class PackageConfiguration implements RootConfiguration {

        private final MethodMetadata factoryMethodMetadata;

        private PackageConfiguration(MethodMetadata factoryMethodMetadata) {
            this.factoryMethodMetadata = factoryMethodMetadata;
        }

        @Override
        public MethodMetadata getFactoryMethodMetadata() {
            return this.factoryMethodMetadata;
        }
    }

    public static class TmpConfiguration implements RootConfiguration {

        @Override
        public MethodMetadata getFactoryMethodMetadata() {
            return new PublicMethodMetadata("tmpMethod");
        }
    }
}
