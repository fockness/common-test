package com.example.demo.cases.designpattern.strategy.bestpractices;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoApplicationTests {
    @Autowired
    private StrategyServiceFactory strategyServiceFactory;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testStrategy() {
        strategyServiceFactory.getMessageService(StrategyType.SMS).sendMessage("Hello world");
    }
}