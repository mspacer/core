package com.core.reflection.timingproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimingDynamicInvocationHandler implements InvocationHandler {
    private final Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long elapsed = System.nanoTime() - start;
        System.out.printf("Executing method %s finished in %d\n", method.getName(), elapsed);
        return result;
    }
}
