package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class LongFieldValueGenerator extends FieldValueGenerator {

    public LongFieldValueGenerator() {
        super(Long.class, long.class);
    }

    @Override
    public Long generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Long generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0l;
    }
}
