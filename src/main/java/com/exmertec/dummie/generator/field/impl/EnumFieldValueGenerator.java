package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class EnumFieldValueGenerator extends FieldValueGenerator {

    public EnumFieldValueGenerator() {
        super(Enum.class);
    }
    @Override
    protected Object defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        Object[] enumConstants = fieldType.getEnumConstants();
        if (enumConstants.length > 0) {
            return enumConstants[0];
        }
        return null;
    }

    @Override
    protected Object randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        Object[] enumConstants = fieldType.getEnumConstants();
        if (enumConstants.length > 0) {
            return enumConstants[new Random().nextInt(enumConstants.length)];
        }
        return null;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return targetFieldType.isEnum();
    }
}
