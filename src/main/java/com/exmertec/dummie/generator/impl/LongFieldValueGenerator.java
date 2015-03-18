package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;

import java.lang.reflect.Field;

public class LongFieldValueGenerator implements FieldValueGenerator {

    @Override
    public Object generate(DummyCache cache, Field field) {
        if (!(field.getType().isAssignableFrom(Long.class) || field.getType().isAssignableFrom(long.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", field.getType(), Long.class, long.class));
        }

        return doGenerate(cache, field);
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        if (!(fieldType.isAssignableFrom(Long.class) || fieldType.isAssignableFrom(long.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", fieldType, Long.class, long.class));
        }

        return doGenerate(cache, fieldType, fieldName);
    }

    protected Long doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0l;
    }

    protected Long doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }
}
