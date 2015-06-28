package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class StringFieldValueGenerator extends FieldValueGenerator {
    public StringFieldValueGenerator() {
        super(String.class);
    }

    @Override
    public String generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public String generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return fieldName;
    }
}
