package com.example.demo.cases.designpattern.dynamicproxy_cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before cglib");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, objects);
        }catch(Exception e){
            System.out.println("e:" + e.getMessage());
            throw e;
        }finally {
            System.out.println("after cglib");
        }
        return result;
    }
}
