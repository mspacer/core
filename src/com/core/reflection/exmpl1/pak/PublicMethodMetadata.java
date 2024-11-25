package com.core.reflection.exmpl1.pak;

import com.core.reflection.exmpl1.MethodMetadata;

public class PublicMethodMetadata implements MethodMetadata {

    private final String publicMethodName;

    public PublicMethodMetadata(String publicMethodName) {
        this.publicMethodName = publicMethodName;
    }

    @Override
    public String methodName() {
        return publicMethodName;
    }
}
