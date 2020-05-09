package com.example.demo.cases.designpattern.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 测试@Autowird在构造函数上的作用
 */
interface Test {
}


@Service
public class TestA implements Test{
}

@Service
class TestB implements Test{

}

@Service
class TestC implements Test{}

@Service
class TestD{}

@Component
class TestE{

    TestD d;
    Test[] test;

    @Autowired
    public TestE(TestD d, Test[] test){
        this.d = d;
        this.test = test;
    }
}
