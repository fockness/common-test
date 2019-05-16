package com.example.demo.cases.designpattern.dynamicproxy_cglib;

import org.springframework.cglib.proxy.Enhancer;

public class Client {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.resquest();
    }
}

class Subject{
    public void resquest(){
        System.out.println("1");
    }
}


