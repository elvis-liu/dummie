package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.Constant;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class MapFieldValueGenerator extends FieldValueGenerator {
    public MapFieldValueGenerator() {
        super(Map.class);
    }

    @Override
    public Object generate(DummyCache cache, Field field) {
        Type genericType = field.getGenericType();
        Map value = Maps.newHashMap();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> keyClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            Class<?> valueClass = (Class<?>) parameterizedType.getActualTypeArguments()[1];

            value.put(cache.getCachedData(keyClass, Constant.DEFAULT_STRING_VALUE), cache.getCachedData(valueClass, Constant.DEFAULT_STRING_VALUE));
        }

        return value;
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return Maps.newHashMap();
    }
}
