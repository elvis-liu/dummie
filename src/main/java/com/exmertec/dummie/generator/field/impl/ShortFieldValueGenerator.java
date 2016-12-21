package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class ShortFieldValueGenerator extends FieldValueGenerator {

    public ShortFieldValueGenerator() {
        super(Short.class, short.class);
    }

    @Override
    protected Short defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0;
    }

    @Override
    protected Short randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return Integer.valueOf(new Random().nextInt()).shortValue();
    }
}
