package com.example.demo.cases.designpattern.strategy.bestpractices;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

@Component
public class StrategyServiceRegistryBuilder extends AbstractEnumRegistryBuilder<StrategyType> {
    @Override
    public StrategyType[] extractKeys(Class<?> builderClass) {
        ForStrategyType forStrategyType = builderClass.getAnnotation(ForStrategyType.class);
        Preconditions.checkNotNull(forStrategyType, "Cannot find @StrategyType annotation on %s",
                builderClass.getName());
        return forStrategyType.value();
    }
}