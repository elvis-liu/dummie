package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class IntegerFieldValueGenerator implements FieldValueGenerator {

    @Override
    public Object generate(DummyCache cache, Field field) {
        if (!(field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(int.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", field.getType(), Integer.class, int.class));
        }

        return doGenerate(cache, field);
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        if (!(fieldType.isAssignableFrom(Integer.class) || fieldType.isAssignableFrom(int.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", fieldType, Integer.class, int.class));
        }

        return doGenerate(cache, fieldType, fieldName);
    }

    protected Integer doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0;
    }

    protected Integer doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }
}
