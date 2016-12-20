package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class BooleanFieldValueGenerator extends FieldValueGenerator {

    public BooleanFieldValueGenerator(GenerationStrategy strategy) {
        super(strategy, Boolean.class, boolean.class);
    }

    @Override
    protected Boolean defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return Boolean.FALSE;
    }

    @Override
    protected Boolean randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return new Random().nextBoolean();
    }
}
