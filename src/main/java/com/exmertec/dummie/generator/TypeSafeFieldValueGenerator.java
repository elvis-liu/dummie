package com.exmertec.dummie.generator;

import com.exmertec.dummie.DummyBuilder;

import java.lang.reflect.Field;

public abstract class TypeSafeFieldValueGenerator<T> implements FieldValueGenerator {
    private final Class<T> fieldType;

    protected TypeSafeFieldValueGenerator(Class<T> fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public T generate(DummyBuilder builder, Field field) {
        if (!field.getType().isAssignableFrom(fieldType)) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s)", field.getType(), fieldType));
        }

        return doGenerate(builder, field);
    }

    protected abstract T doGenerate(DummyBuilder builder, Field field);
}
