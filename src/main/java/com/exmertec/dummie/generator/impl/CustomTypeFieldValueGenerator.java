package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.DummieException;
import com.exmertec.dummie.generator.Inflater;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

public class CustomTypeFieldValueGenerator<T> extends FieldValueGenerator {
    private final Class<T> type;

    public CustomTypeFieldValueGenerator(Class<T> type) {
        super();
        this.type = type;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return true;
    }

    @Override
    protected T defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return randomGenerator(cache, fieldType, fieldName);
    }

    @Override
    protected T randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        try {
            T instance = type.newInstance();

            cache.cacheData(fieldType, fieldName, instance);

            Inflater.inflateInstance(instance, cache, type);
            return instance;
        } catch (Exception e) {
            throw new DummieException(e);
        }
    }
}
