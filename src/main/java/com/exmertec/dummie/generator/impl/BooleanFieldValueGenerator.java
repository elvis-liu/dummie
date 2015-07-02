package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class BooleanFieldValueGenerator extends FieldValueGenerator {

    public BooleanFieldValueGenerator() {
        super(Boolean.class, boolean.class);
    }

    protected Boolean doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return false;
    }

    protected Boolean doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return Boolean.FALSE;
    }
}
