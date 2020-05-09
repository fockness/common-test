package com.example.demo.cases.designpattern.strategy.bestpractices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StrategyServiceFactory {

    private final Map<StrategyType, StrategyService> messageServiceMap;

    @Autowired
    public StrategyServiceFactory(StrategyServiceRegistryBuilder strategyServiceRegistryBuilder,
            StrategyService[] strategyServices) {
        messageServiceMap = strategyServiceRegistryBuilder.build(strategyServices);
    }

    public StrategyService getMessageService(StrategyType strategyType) {
        StrategyService strategyService = messageServiceMap.get(strategyType);
        if (strategyService == null) {
            throw new RuntimeException("未能找到对应的处理类");
        }
        return strategyService;
    }
}