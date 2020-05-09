package com.example.demo.cases.designpattern.strategy.bestpractices;

import org.springframework.stereotype.Service;

@Service
@ForStrategyType(
        StrategyType.SMS
)
public class SmsStrategyServiceImpl implements StrategyService {
    @Override
    public void sendMessage(String context) {
        System.out.println("sms:" + context);
    }
}