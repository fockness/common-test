package com.example.demo.cases.designpattern.strategy.bestpractices;

public enum StrategyType {
    SMS(1, "短信"),
    MQ(2, "mq消息");

    StrategyType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
}