package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class ShortFieldValueGenerator extends FieldValueGenerator {

    public ShortFieldValueGenerator() {
        super(Short.class, short.class);
    }

    @Override
    protected Short defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0;
    }

    @Override
    protected Short randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return Integer.valueOf(new Random().nextInt()).shortValue();
    }
}
