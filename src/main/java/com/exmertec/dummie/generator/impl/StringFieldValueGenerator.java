package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;

import java.lang.reflect.Field;

public class StringFieldValueGenerator extends TypeSafeFieldValueGenerator<String> {
    public StringFieldValueGenerator() {
        super(String.class);
    }

    @Override
    protected String doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return fieldName;
    }

    @Override
    protected String doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(),field.getName());
    }
}
