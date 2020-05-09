package com.example.demo.cases.designpattern.strategy.bestpractices;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ForStrategyType {
    StrategyType[] value();
}