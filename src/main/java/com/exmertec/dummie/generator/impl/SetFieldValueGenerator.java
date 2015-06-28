package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.Constant;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class SetFieldValueGenerator extends FieldValueGenerator {

    public SetFieldValueGenerator() {
        super(Set.class);
    }

    @Override
    public Set generate(DummyCache cache, Field field) {
        Type genericType = field.getGenericType();
        Set value = Sets.newHashSet();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> setClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

            value.add(cache.getCachedData(setClass, Constant.DEFAULT_STRING_VALUE));
        }

        return value;
    }

    @Override
    public Set generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return Sets.newHashSet();
    }
}
