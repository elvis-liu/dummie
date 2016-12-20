package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class IntegerFieldValueGenerator extends FieldValueGenerator {

    public IntegerFieldValueGenerator(GenerationStrategy strategy) {
        super(strategy, Integer.class, int.class);
    }

    @Override
    protected Integer defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return 0;
    }

    @Override
    protected Integer randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return new Random().nextInt();
    }
}
