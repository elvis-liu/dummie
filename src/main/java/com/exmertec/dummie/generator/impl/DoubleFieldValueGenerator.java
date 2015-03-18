package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;

import java.lang.reflect.Field;

public class DoubleFieldValueGenerator implements FieldValueGenerator {

    @Override
    public Object generate(DummyCache cache, Field field) {
        if (!(field.getType().isAssignableFrom(Double.class) || field.getType().isAssignableFrom(double.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", field.getType(), Double.class, double.class));
        }

        return doGenerate(cache, field);
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        if (!(fieldType.isAssignableFrom(Double.class) || fieldType.isAssignableFrom(double.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", fieldType, Double.class, double.class));
        }

        return doGenerate(cache, fieldType, fieldName);
    }

    protected Double doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0d;
    }

    protected Double doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }
}
