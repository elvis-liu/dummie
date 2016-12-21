package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class FloatFieldValueGenerator extends FieldValueGenerator {

    public FloatFieldValueGenerator() {
        super(Float.class, float.class);
    }

    @Override
    protected Float defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0F;
    }

    @Override
    protected Float randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextFloat();
    }
}
