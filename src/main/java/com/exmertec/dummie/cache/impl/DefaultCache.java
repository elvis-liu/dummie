package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.impl.EnumFieldValueGenerator;
import com.exmertec.dummie.generator.impl.ListFieldValueGenerator;
import com.exmertec.dummie.generator.impl.StringFieldValueGenerator;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class DefaultCache implements DummyCache {
    private final Map<Class<?>, FieldValueGenerator> cachedGenerator;
    private final Map<Class<?>, FieldValueGenerator> cachedGeneratorWithSuperGenerator;
    private final Map<Class<?>, Object> cachedData;

    public DefaultCache() {
        cachedGenerator = Maps.newHashMap();
        addDefaultGenerators();

        cachedGeneratorWithSuperGenerator = Maps.newHashMap();
        addDefaultSuperClassGenerators();

        cachedData = Maps.newHashMap();
        appendPrimitiveWrappers();
    }

    private void addDefaultSuperClassGenerators() {
        cachedGeneratorWithSuperGenerator.put(Enum.class, new EnumFieldValueGenerator());
    }

    private void addDefaultGenerators() {
        cachedGenerator.put(String.class, new StringFieldValueGenerator());
        cachedGenerator.put(List.class, new ListFieldValueGenerator());
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

    @Override
    public FieldValueGenerator getCachedGenerator(Class<?> dataType) {
        FieldValueGenerator generator = cachedGenerator.get(dataType);
        if (generator == null) {
            generator = cachedGeneratorWithSuperGenerator.get(dataType.getSuperclass());
        }

        return generator;
    }

    @Override
    public void cacheGenerator(Class<?> dataType, FieldValueGenerator generator) {
        cachedGenerator.put(dataType, generator);
    }
}
