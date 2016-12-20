package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class LongFieldValueGenerator extends FieldValueGenerator {

    public LongFieldValueGenerator() {
        super(Long.class, long.class);
    }

    @Override
    protected Long defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0L;
    }

    @Override
    protected Long randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return new Random().nextLong();
    }
}
