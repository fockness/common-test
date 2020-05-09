package com.example.demo.cases.designpattern.strategy.bestpractices;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public abstract class AbstractEnumRegistryBuilder<E extends Enum> {

    public <T> Map<E, T> build(T[] beans) {
        ImmutableMap.Builder<E, T> mapBuilder = ImmutableMap.builder();
        for (T bean : beans) {
            Class<?> builderClass = bean.getClass();
            E[] keys = extractKeys(builderClass);
            for (E key : keys) {
                mapBuilder.put(key, bean);
            }
        }
        return mapBuilder.build();
    }

    public abstract E[] extractKeys(Class<?> builderClass);
}