package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.Constant;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapFieldValueGenerator extends FieldValueGenerator {
    public MapFieldValueGenerator(GenerationStrategy strategy) {
        super(strategy, Map.class);
    }

    // TODO: not support enum map
    private Map generateValue(Class<?> type) {
        Map value = null;
        if (super.isMatchType(type)) {
            value = new HashMap();
        } else {
            try {
                value = (Map) type.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    @Override
    public Map generate(DummyCache cache, Field field) {
        Map value = generateValue(field.getType());

        Type genericType = field.getGenericType();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> keyClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            Class<?> valueClass = (Class<?>) parameterizedType.getActualTypeArguments()[1];

            value.put(cache.getCachedData(keyClass, Constant.DEFAULT_STRING_VALUE),
                cache.getCachedData(valueClass, Constant.DEFAULT_STRING_VALUE));
        }

        return value;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return Map.class.isAssignableFrom(targetFieldType);
    }

    @Override
    public Map generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return generateValue(fieldType);
    }

    @Override
    protected Object defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return null;
    }

    @Override
    protected Object randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return null;
    }
}
