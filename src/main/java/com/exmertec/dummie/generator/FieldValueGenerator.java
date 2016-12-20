package com.exmertec.dummie.generator;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;

import java.lang.reflect.Field;

public abstract class FieldValueGenerator {
    private final Class<?>[] fieldTypes;
    protected final GenerationStrategy strategy;

    protected FieldValueGenerator(GenerationStrategy strategy, Class<?>... fieldTypes) {
        this.strategy = strategy;
        this.fieldTypes = fieldTypes;
    }
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        switch (strategy) {
            case RANDOM:
                return randomGenerator(cache, fieldType, fieldName);
            case DEFAULT:
            default:
                return defaultGenerator(cache, fieldType, fieldName);
        }
    }

    protected abstract Object defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName);

    protected abstract Object randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName);

    public boolean isMatchType(Class<?> targetFieldType) {
        for (Class<?> fieldType: fieldTypes) {
            if (targetFieldType.isAssignableFrom(fieldType)) {
                return true;
            }
        }
        return false;
    }
}
