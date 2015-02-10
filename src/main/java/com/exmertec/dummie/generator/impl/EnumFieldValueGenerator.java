package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class EnumFieldValueGenerator implements FieldValueGenerator {
    @Override
    public Object generate(DummyCache cache, Field field) {
        Class genericType = field.getType();
        Object[] genericValues = genericType.getEnumConstants();
        if (genericValues.length > 0) {
            return genericValues[0];
        }
        return null;
    }
}
