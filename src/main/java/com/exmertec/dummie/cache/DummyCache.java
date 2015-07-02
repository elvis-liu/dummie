package com.exmertec.dummie.cache;

import java.lang.reflect.Field;

import com.exmertec.dummie.cache.impl.KeyValueDataCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

public abstract class DummyCache implements GeneratorCache {

    private DataCache dataCache;

    public DummyCache() {
        this.dataCache = new KeyValueDataCache();
    }

    public DummyCache(DataCache dataCache) {
        this.dataCache = dataCache;
    }

    public Object getCachedData(Field field) {
        Class<?> fieldType = field.getType();
        Object value = dataCache.getCachedData(fieldType, field.getName());
        if (value == null) {
            FieldValueGenerator generator = getCachedGenerator(fieldType);
            if (generator != null) {
                value = generator.generate(this, field);
            }
            dataCache.cacheData(fieldType, field.getName(), value);
        }
        return value;
    }

    public Object getCachedData(Class<?> dataType, String key) {
        Object value = dataCache.getCachedData(dataType, key);
        if (value == null) {
            FieldValueGenerator generator = getCachedGenerator(dataType);
            if (generator != null) {
                value = generator.generate(this, dataType, key);
            }
            try {
                dataCache.cacheData(dataType, key, Class.forName(dataType.getName()).cast(value));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public <T> void cacheData(Class<T> dataType, String key, Object value) {
        dataCache.cacheData(dataType, key, value);
    }

}
