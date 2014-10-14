package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DataCache;
import com.google.common.collect.Maps;

import java.util.Map;

public class BasicDataCache implements DataCache {
    private final Map<Class<?>, Object> cachedData;

    public BasicDataCache() {
        cachedData = Maps.newHashMap();
        appendPrimitiveWrappers();
    }

    private void appendPrimitiveWrappers() {
        cachedData.put(Boolean.class, false);
        cachedData.put(Byte.class, (byte) 0);
        cachedData.put(Character.class, '\u0000');
        cachedData.put(Double.class, (double) 0);
        cachedData.put(Float.class, (float) 0);
        cachedData.put(Integer.class, 0);
        cachedData.put(Long.class, 0l);
        cachedData.put(Short.class, (short) 0);
    }

    @Override
    public <T> void cacheData(Class<T> dataType, T value) {
        cachedData.put(dataType, value);
    }

    @Override
    public <T> T getCachedData(Class<T> dataType) {
        return (T) cachedData.get(dataType);
    }
}
