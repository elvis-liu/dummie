package com.exmertec.dummie.generator.field;

import com.exmertec.dummie.cache.Constant;
import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.configuration.GenerationStrategy;

import java.lang.reflect.Field;
import java.util.UUID;

public abstract class FieldValueGenerator {
    private final Class<?>[] fieldTypes;
    protected GenerationStrategy strategy;

    protected FieldValueGenerator(Class<?>... fieldTypes) {
        this(GenerationStrategy.DEFAULT, fieldTypes);
    }

    protected FieldValueGenerator(GenerationStrategy strategy, Class<?>... fieldTypes) {
        this.strategy = strategy;
        this.fieldTypes = fieldTypes;
    }

    public Object generate(DataGenerator dataGenerator, Field field) {
        return generate(dataGenerator, field.getType(), field.getName());
    }

    public Object generate(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        switch (strategy) {
            case RANDOM:
                return randomGenerator(dataGenerator, fieldType, fieldName);
            case DEFAULT:
            default:
                return defaultGenerator(dataGenerator, fieldType, fieldName);
        }
    }

    protected String generateKeyValue() {
        return strategy == GenerationStrategy.RANDOM ? UUID.randomUUID().toString() : Constant.DEFAULT_STRING_VALUE;
    }

    protected Object defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return null;
    }

    protected Object randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return null;
    }

    public boolean isMatchType(Class<?> targetFieldType) {
        for (Class<?> fieldType: fieldTypes) {
            if (targetFieldType.isAssignableFrom(fieldType)) {
                return true;
            }
        }
        return false;
    }

    public void setStrategy(GenerationStrategy strategy) {
        this.strategy = strategy;
    }
}
