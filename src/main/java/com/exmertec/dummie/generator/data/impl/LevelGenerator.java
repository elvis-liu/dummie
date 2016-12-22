package com.exmertec.dummie.generator.data.impl;

import com.exmertec.dummie.DummieException;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.Inflater;
import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Stack;

public class LevelGenerator extends DataGenerator {

    private Integer floor;

    private Stack<Class<?>> parents;

    public LevelGenerator(GenerationStrategy strategy, Integer floor) {
        super(strategy);

        this.floor = floor;
        this.parents = new Stack<Class<?>>();
    }

     private boolean isOverFloor(Field field) {
        if (parents.empty()) {
            parents.push(field.getDeclaringClass());
        } else if (!parents.peek().equals(field.getDeclaringClass())) {
            if (parents.size() >= floor) {
                return true;
            }
            parents.push(field.getDeclaringClass());
        }
        return false;
    }

    private void downstream(Field field) {
        if (!parents.empty()) {
            Class<?> fieldType;
            Type genericType = field.getGenericType();
            if (ParameterizedType.class.isInstance(genericType)) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                fieldType = (Class<?>) actualTypeArguments[actualTypeArguments.length - 1];
            } else {
                fieldType = field.getType();
            }
            if (fieldType.equals(parents.peek())) {
                parents.pop();
            }
        }
    }

    public Object getData(Field field) {
        Object value = isOverFloor(field) ? null : super.getData(field);
        downstream(field);
        return value;
    }

    @Override
    protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
        return new FieldValueGenerator() {
            @Override
            public Object generate(DataGenerator dataGenerator, Field field) {
                return generate(dataGenerator, field.getType(), field.getName());
            }

            @Override
            public Object generate(DataGenerator dataGenerator, Class fieldType, String fieldName) {
                try {
                    Object instance = fieldType.newInstance();
                    Inflater.inflateInstance(instance, dataGenerator, fieldType);
                    return instance;
                } catch (Exception e) {
                    throw new DummieException(e);
                }
            }
        };
    }
}
