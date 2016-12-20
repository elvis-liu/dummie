package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class DoubleFieldValueGenerator extends FieldValueGenerator {

    public DoubleFieldValueGenerator() {
        super(Double.class, double.class);
    }

    @Override
    protected Double defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0d;
    }

    @Override
    protected Double randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return new Random().nextDouble();
    }
}
