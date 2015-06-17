package com.exmertec.dummie.generator.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import com.exmertec.dummie.cache.Constant;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

public class SetFieldValueGenerator extends FieldValueGenerator {

    public SetFieldValueGenerator() {
        super(Set.class);
    }

    private Set generateValue(Class<?> type) {
        Set value = null;
        if (super.isMatchType(type)) {
            value = new HashSet();
        } else {
            try {
                value = (Set) type.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return value;
    }

    @Override
    public Set generate(DummyCache cache, Field field) {
        Set value = generateValue(field.getType());

        Type genericType = field.getGenericType();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> setClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

            value.add(cache.getCachedData(setClass, Constant.DEFAULT_STRING_VALUE));
        }

        return value;
    }

    @Override
    public Set generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return generateValue(fieldType);
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return Set.class.isAssignableFrom(targetFieldType);
    }
}
