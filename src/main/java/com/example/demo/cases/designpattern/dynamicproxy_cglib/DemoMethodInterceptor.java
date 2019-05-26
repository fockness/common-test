package com.example.demo.cases.designpattern.dynamicproxy_cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param target 目标对象
     * @param method 目标方法
     * @param objects 目标方法的参数
     * @param methodProxy 目标方法的代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before cglib");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(target, objects);
            //method.invoke(target, objects);上一行与这行一个意思
        }catch(Exception e){
            System.out.println("e:" + e.getMessage());
            throw e;
        }finally {
            System.out.println("after cglib");
        }
        return result;
    }
}
