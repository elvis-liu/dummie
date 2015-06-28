package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class ShortFieldValueGenerator extends FieldValueGenerator {

    public ShortFieldValueGenerator() {
        super(Short.class, short.class);
    }

    @Override
    public Short generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Short generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0;
    }
}
