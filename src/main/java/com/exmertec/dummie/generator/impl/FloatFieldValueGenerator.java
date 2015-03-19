package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class FloatFieldValueGenerator extends FieldValueGenerator {

    public FloatFieldValueGenerator() {
        super(Float.class, float.class);
    }

    @Override
    public Float generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Float generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0f;
    }
}
