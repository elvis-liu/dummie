package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class ByteFieldValueGenerator extends FieldValueGenerator {

    public ByteFieldValueGenerator() {
        super(Byte.class, byte.class);
    }

    @Override
    public Byte generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Byte generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return (byte) 0;
    }
}
