package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class BooleanFieldValueGenerator extends FieldValueGenerator {

    public BooleanFieldValueGenerator() {
        super(Boolean.class, boolean.class);
    }

    @Override
    protected Boolean defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return Boolean.FALSE;
    }

    @Override
    protected Boolean randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextBoolean();
    }
}
