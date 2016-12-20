package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.impl.CustomTypeFieldValueGenerator;

import java.lang.reflect.Field;

public class DefaultCache extends DummyCache {

    public DefaultCache(GenerationStrategy generationStrategy) {
        super(generationStrategy);
    }

    @Override
    public Object getCachedData(Field field) {
        Object value = super.getCachedData(field);
        cacheData(field.getType(), field.getName(), value);

        return value;
    }

    @Override
    public Object getCachedData(Class<?> dataType, String key) {
        Object value = super.getCachedData(dataType, key);

        try {
            cacheData(dataType, key, Class.forName(dataType.getName()).cast(value));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return value;
    }

    @Override
    protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
        return new CustomTypeFieldValueGenerator(dataType);
    }
}
