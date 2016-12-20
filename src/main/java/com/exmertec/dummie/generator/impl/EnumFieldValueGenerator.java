package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class EnumFieldValueGenerator extends FieldValueGenerator {

    public EnumFieldValueGenerator() {
        super(Enum.class);
    }
    @Override
    protected Object defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        Object[] enumConstants = fieldType.getEnumConstants();
        if (enumConstants.length > 0) {
            return enumConstants[0];
        }
        return null;
    }

    @Override
    protected Object randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
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
