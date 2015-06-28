package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class IntegerFieldValueGenerator extends FieldValueGenerator {

    public IntegerFieldValueGenerator() {
        super(Integer.class, int.class);
    }

    @Override
    public Integer generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Integer generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0;
    }
}
