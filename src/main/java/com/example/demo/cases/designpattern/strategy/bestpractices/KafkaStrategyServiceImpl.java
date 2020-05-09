package com.example.demo.cases.designpattern.strategy.bestpractices;

import org.springframework.stereotype.Service;

@Service
@ForStrategyType(
        StrategyType.MQ
)
public class KafkaStrategyServiceImpl implements StrategyService {
    @Override
    public void sendMessage(String context) {
        System.out.println("mq:" + context);
    }
}