package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DataCache;

import java.util.HashMap;
import java.util.Map;

public class BasicDataCache implements DataCache {
    protected final Map<Class<?>, Object> cachedData;

    private final Map<Class<?>, Class<?>> primToWrap = new HashMap<Class<?>, Class<?>>(16);

    public BasicDataCache() {
        appendPrimitiveWrappers();
        cachedData = new HashMap<Class<?>, Object>();
    }

    private void appendPrimitiveWrappers() {
        primToWrap.put(boolean.class, Boolean.class);
        primToWrap.put(byte.class, Byte.class);
        primToWrap.put(char.class, Character.class);
        primToWrap.put(double.class, Double.class);
        primToWrap.put(float.class, Float.class);
        primToWrap.put(int.class, Integer.class);
        primToWrap.put(long.class, Long.class);
        primToWrap.put(short.class, Short.class);
        primToWrap.put(void.class, Void.class);
    }

    protected Class<?> normalize(Class<?> dataType) {
        if (dataType.isPrimitive()) {
            return primToWrap.get(dataType);
        }
        return dataType;
    }

    @Override
    public <T> void cacheData(Class<T> dataType, Object value) {
        if (dataType.isPrimitive()) {
            dataType = (Class<T>) primToWrap.get(dataType);
        }
        if (!(value == null || dataType.isInstance(value))) {
            throw new ClassCastException(value + " cannot be cast to " + dataType);
        }
        if (!cachedData.containsKey(dataType)) {
            cachedData.put(dataType, value);
        }
    }

    @Override
    public <T> void cacheData(Class<T> dataType, final String key, final Object value) {
        if (dataType.isPrimitive()) {
            dataType = (Class<T>) primToWrap.get(dataType);
        }
        if (!(value == null || dataType.isInstance(value))) {
            throw new ClassCastException(value + " cannot be cast to " + dataType);
        }
        if (!cachedData.containsKey(dataType)) {
            cachedData.put(dataType, value);
        }
    }

    @Override
    public <T> T getCachedData(Class<T> dataType, String key) {
        return (T) cachedData.get(dataType);
    }
}
