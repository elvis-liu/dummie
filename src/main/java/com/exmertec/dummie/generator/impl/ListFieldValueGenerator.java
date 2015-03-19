package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.Constant;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListFieldValueGenerator extends FieldValueGenerator {
    public ListFieldValueGenerator() {
        super(List.class);
    }

    @Override
    public List generate(DummyCache cache, Field field) {
        Type genericType = field.getGenericType();
        List value = Lists.newArrayList();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> listClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

            value.add(cache.getCachedData(listClass, Constant.DEFAULT_STRING_VALUE));
        }

        return value;
    }

    @Override
    public List generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return Lists.newArrayList();
    }
}
