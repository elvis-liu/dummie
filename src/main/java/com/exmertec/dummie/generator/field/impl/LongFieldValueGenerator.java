package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class LongFieldValueGenerator extends FieldValueGenerator {

    public LongFieldValueGenerator() {
        super(Long.class, long.class);
    }

    @Override
    protected Long defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0L;
    }

    @Override
    protected Long randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextLong();
    }
}
