package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class DoubleFieldValueGenerator extends FieldValueGenerator {

    public DoubleFieldValueGenerator() {
        super(Double.class, double.class);
    }

    @Override
    protected Double defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0d;
    }

    @Override
    protected Double randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextDouble();
    }
}
