package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListFieldValueGenerator extends FieldValueGenerator {
    public ListFieldValueGenerator(GenerationStrategy strategy) {
        super(strategy, List.class);
    }

    private List generateValue(Class<?> type) {
        List value = null;
        if (super.isMatchType(type)) {
            value = new ArrayList();
        } else {
            try {
                value = (List) type.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    @Override
    public List generate(DummyCache cache, Field field) {
        List value = generateValue(field.getType());
        Type genericType = field.getGenericType();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> listClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

            value.add(cache.getCachedData(listClass, generateKeyValue()));
        }

        return value;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return List.class.isAssignableFrom(targetFieldType);
    }

    @Override
    public List generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return generateValue(fieldType);
    }

    @Override
    protected List defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return null;
    }

    @Override
    protected List randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return null;
    }
}
