package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.UUID;

public class StringFieldValueGenerator extends FieldValueGenerator {
    public StringFieldValueGenerator() {
        super(String.class);
    }

    @Override
    protected String defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return fieldName;
    }

    @Override
    protected String randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return UUID.randomUUID().toString();
    }
}
