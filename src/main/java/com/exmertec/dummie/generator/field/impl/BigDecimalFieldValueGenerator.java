package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.math.BigDecimal;
import java.util.Random;

public class BigDecimalFieldValueGenerator extends FieldValueGenerator {
    public BigDecimalFieldValueGenerator() {
        super(BigDecimal.class);
    }

    @Override
    protected BigDecimal defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new BigDecimal(new Random().nextInt());
    }

}
