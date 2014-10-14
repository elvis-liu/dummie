package com.exmertec.dummie.impl;

import com.exmertec.dummie.DummyCache;
import com.google.common.collect.Maps;

import java.util.Map;

public class BasicDummyCache implements DummyCache {
    private final Map<Class<?>, Object> cachedData;

    public BasicDummyCache() {
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
    public <T> void put(Class<T> dataType, T value) {
        cachedData.put(dataType, value);
    }

    @Override
    public <T> T get(Class<T> dataType) {
        return (T) cachedData.get(dataType);
    }
}
