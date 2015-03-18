package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;

import java.lang.reflect.Field;

public class ByteFieldValueGenerator implements FieldValueGenerator {

    @Override
    public Object generate(DummyCache cache, Field field) {
        if (!(field.getType().isAssignableFrom(Byte.class) || field.getType().isAssignableFrom(byte.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", field.getType(), Byte.class, byte.class));
        }

        return doGenerate(cache, field);
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        if (!(fieldType.isAssignableFrom(Byte.class) || fieldType.isAssignableFrom(byte.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", fieldType, Byte.class, byte.class));
        }

        return doGenerate(cache, fieldType, fieldName);
    }

    protected Byte doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return (byte) 0;
    }

    protected Byte doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }
}
