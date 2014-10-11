package com.exmertec.dummy_easy;

import com.exmertec.dummy_easy.generator.BooleanGenerator;

import java.util.HashMap;
import java.util.Map;

public class DummyDataGeneratorFactory {
    private Map<Class<?>, DummyDataGenerator> generatorMap;

    public DummyDataGeneratorFactory() {
        this.generatorMap = new HashMap<Class<?>, DummyDataGenerator>();
        generatorMap.put(Boolean.class, new BooleanGenerator(false));
    }

    public <T> DummyDataGenerator getGenerator(Class<T> type) {
        return generatorMap.get(type);
    }
}
