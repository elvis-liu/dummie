package com.exmertec.dummy_easy.generator;

import java.lang.reflect.Field;

public abstract class TypeSafeFieldValueGenerator<T> implements FieldValueGenerator {
    private final Class<T> fieldType;

    protected TypeSafeFieldValueGenerator(Class<T> fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public T generate(Field field) {
        if (!field.getType().isAssignableFrom(fieldType)) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s)", field.getType(), fieldType));
        }

        return doGenerate(field);
    }

    protected abstract T doGenerate(Field field);
}
