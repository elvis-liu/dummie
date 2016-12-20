package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.DummieException;
import com.exmertec.dummie.Inflater;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class CustomTypeFieldValueGenerator<T> extends FieldValueGenerator {
    private final Class<T> type;

    public CustomTypeFieldValueGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return true;
    }

    @Override
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
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
