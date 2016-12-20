package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class FloatFieldValueGenerator extends FieldValueGenerator {

    public FloatFieldValueGenerator() {
        super(Float.class, float.class);
    }

    @Override
    protected Float defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0F;
    }

    @Override
    protected Float randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return new Random().nextFloat();
    }
}
