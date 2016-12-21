package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class IntegerFieldValueGenerator extends FieldValueGenerator {

    public IntegerFieldValueGenerator() {
        super(Integer.class, int.class);
    }

    @Override
    protected Integer defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0;
    }

    @Override
    protected Integer randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextInt();
    }
}
