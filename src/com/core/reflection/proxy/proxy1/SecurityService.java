package com.core.reflection.proxy.proxy1;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SecurityService {
    public void onInvoke(SecurityLevelType level, Method method, Object[] args) {
        System.out.printf("%s() was invoked with parameters: %s ", method.getName(), Arrays.toString(args));
        switch (level) {
            case LOW: System.out.println("security: " + level);
                    break;
            case MEDIUM: System.out.println("security: " + level);
                    break;
            case HIGH: System.out.println("security: " + level);
                    break;
            default: throw new EnumConstantNotPresentException(SecurityLevelType.class, level.toString());
        }
    }
}
