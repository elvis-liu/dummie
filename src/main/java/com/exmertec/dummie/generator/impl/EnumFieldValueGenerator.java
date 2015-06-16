package com.exmertec.dummie.generator.impl;

import java.lang.reflect.Field;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

public class EnumFieldValueGenerator extends FieldValueGenerator {

    public EnumFieldValueGenerator() {
        super(Enum.class);
    }
    @Override
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        Object[] enumConstants = fieldType.getEnumConstants();
        if (enumConstants.length > 0) {
            return enumConstants[0];
        }
        return null;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return targetFieldType.isEnum();
    }
}
