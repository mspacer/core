package com.core.reflection.proxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class SimpleProxyTest {
    public static void main(String[] args) {

        Map proxyInstance = (Map) Proxy.newProxyInstance(
                SimpleProxyTest.class.getClassLoader(),
                new Class[] { Map.class },
                new DynamicInvocationHandler(new HashMap<String, String>()));

        proxyInstance.put("hello", "world");
        System.out.println(proxyInstance.size());
        System.out.println(proxyInstance.get("hello"));

        Map proxyProxyInstance = (Map) Proxy.newProxyInstance(
                SimpleProxyTest.class.getClassLoader(),
                new Class[] { Map.class },
                new DynamicInvocationHandler(proxyInstance));

        proxyProxyInstance.put("hello1", "world1");
        System.out.println(proxyProxyInstance.get("hello1"));

    }
}
