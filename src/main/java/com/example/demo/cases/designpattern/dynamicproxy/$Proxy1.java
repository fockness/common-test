package com.example.demo.cases.designpattern.dynamicproxy;

import java.lang.reflect.Method;

/**
 * 这个是Proxy生成的代理类
 */
public class $Proxy1 implements Moveable {
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }

    com.example.demo.cases.designpattern.dynamicproxy.InvocationHandler h;

    @Override
    public void move() {
        try {
            Method md = Moveable.class.getMethod("move");
            h.invoke(this, md);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}