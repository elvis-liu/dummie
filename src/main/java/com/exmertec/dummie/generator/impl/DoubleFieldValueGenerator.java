package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class DoubleFieldValueGenerator extends FieldValueGenerator {

    public DoubleFieldValueGenerator() {
        super(Double.class, double.class);
    }

    @Override
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0d;
    }
}
