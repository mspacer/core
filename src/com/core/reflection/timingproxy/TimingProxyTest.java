package com.core.reflection.timingproxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class TimingProxyTest {
    public static void main(String[] args) {
        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                TimingProxyTest.class.getClassLoader(),
                new Class[] {Map.class},
                new TimingDynamicInvocationHandler(new HashMap<String, Object>()));

        mapProxyInstance.put("var1", "String");
        mapProxyInstance.get("var1");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                TimingProxyTest.class.getClassLoader(),
                new Class[] { CharSequence.class },
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.length();
    }
}
