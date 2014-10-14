package com.exmertec.dummie.generator;

import com.exmertec.dummie.cache.DummyCache;

import java.lang.reflect.Field;

public abstract class TypeSafeFieldValueGenerator<T> implements FieldValueGenerator {
    private final Class<T> fieldType;

    protected TypeSafeFieldValueGenerator(Class<T> fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public T generate(DummyCache cache, Field field) {
        if (!field.getType().isAssignableFrom(fieldType)) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s)", field.getType(), fieldType));
        }

        return doGenerate(cache, field);
    }

    protected abstract T doGenerate(DummyCache cache, Field field);
}
