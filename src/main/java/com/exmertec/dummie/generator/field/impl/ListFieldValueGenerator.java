package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListFieldValueGenerator extends FieldValueGenerator {
    public ListFieldValueGenerator() {
        super(List.class);
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
    public List generate(DataGenerator dataGenerator, Field field) {
        List value = generateValue(field.getType());
        Type genericType = field.getGenericType();
        if (ParameterizedType.class.isInstance(genericType)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Class<?> listClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

            value.add(dataGenerator.getData(listClass, generateKeyValue()));
        }

        return value;
    }

    @Override
    public boolean isMatchType(Class<?> targetFieldType) {
        return List.class.isAssignableFrom(targetFieldType);
    }

    @Override
    public List generate(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return generateValue(fieldType);
    }
}
