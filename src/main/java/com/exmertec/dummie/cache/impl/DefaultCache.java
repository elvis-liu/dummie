package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DummyCache;

import java.lang.reflect.Field;

public class DefaultCache extends DummyCache {

    @Override
    public Object getCachedData(Field field) {
        Object value = super.getCachedData(field);
        dataCache.cacheData(field.getType(), field.getName(), value);

        return value;
    }

    @Override
    public Object getCachedData(Class<?> dataType, String key) {
        Object value = super.getCachedData(dataType, key);

        try {
            dataCache.cacheData(dataType, key, Class.forName(dataType.getName()).cast(value));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return value;
    }
}
