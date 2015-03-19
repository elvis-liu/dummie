package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

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
        return super.isMatchType(targetFieldType.getSuperclass());
    }
}
