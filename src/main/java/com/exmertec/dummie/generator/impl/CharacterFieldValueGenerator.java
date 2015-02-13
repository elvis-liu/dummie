package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;

import java.lang.reflect.Field;

public class CharacterFieldValueGenerator implements FieldValueGenerator {

    @Override
    public Object generate(DummyCache cache, Field field) {
        if (!(field.getType().isAssignableFrom(Character.class) || field.getType().isAssignableFrom(char.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", field.getType(), Character.class, char.class));
        }

        return doGenerate(cache, field);
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        if (!(fieldType.isAssignableFrom(Character.class) || fieldType.isAssignableFrom(char.class))) {
            throw new IllegalStateException(
                    String.format("Field type (%s) doesn't match with generator type (%s or %s)", fieldType, Character.class, char.class));
        }

        return doGenerate(cache, fieldType, fieldName);
    }

    protected Character doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return '\u0000';
    }

    protected Character doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }
}
