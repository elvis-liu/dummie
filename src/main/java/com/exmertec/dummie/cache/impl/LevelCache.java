package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.DummyBuilder;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Stack;

public class LevelCache extends DummyCache {

    private Integer floor;

    private Stack<Class<?>> parents;

    public LevelCache(GenerationStrategy strategy, Integer floor) {
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

    public Object getCachedData(Field field) {
        Object value = isOverFloor(field) ? null : super.getCachedData(field);
        downstream(field);
        return value;
    }

    @Override
    protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
        return new FieldValueGenerator() {
            @Override
            public Object generate(DummyCache cache, Field field) {
                return generate(cache, field.getType(), field.getName());
            }

            @Override
            public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
                return new DummyBuilder(fieldType, cache).build();
            }

            @Override
            protected Object defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
                return null;
            }

            @Override
            protected Object randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
                return null;
            }
        };
    }
}
