package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.impl.*;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class DefaultCache extends DummyCache {
    private final Map<Class<?>, FieldValueGenerator> cachedGenerator;
    private final Map<Class<?>, FieldValueGenerator> cachedGeneratorWithSuperGenerator;

    public DefaultCache() {
        cachedGenerator = Maps.newHashMap();
        addDefaultGenerators();

        cachedGeneratorWithSuperGenerator = Maps.newHashMap();
        addDefaultSuperClassGenerators();
    }

    private void addDefaultSuperClassGenerators() {
        cachedGeneratorWithSuperGenerator.put(Enum.class, new EnumFieldValueGenerator());
    }

    private void addDefaultGenerators() {
        cachedGenerator.put(String.class, new StringFieldValueGenerator());
        cachedGenerator.put(List.class, new ListFieldValueGenerator());
        cachedGenerator.put(Boolean.class, new BooleanFieldValueGenerator());
        cachedGenerator.put(Byte.class,  new ByteFieldValueGenerator());
        cachedGenerator.put(Character.class, new CharacterFieldValueGenerator());
        cachedGenerator.put(Double.class, new DoubleFieldValueGenerator());
        cachedGenerator.put(Float.class, new FloatFieldValueGenerator());
        cachedGenerator.put(Integer.class, new IntegerFieldValueGenerator());
        cachedGenerator.put(Long.class,new LongFieldValueGenerator());
        cachedGenerator.put(Short.class, new ShortFieldValueGenerator());

        cachedGenerator.put(boolean.class, new BooleanFieldValueGenerator());
        cachedGenerator.put(byte.class,  new ByteFieldValueGenerator());
        cachedGenerator.put(char.class, new CharacterFieldValueGenerator());
        cachedGenerator.put(double.class, new DoubleFieldValueGenerator());
        cachedGenerator.put(float.class, new FloatFieldValueGenerator());
        cachedGenerator.put(int.class, new IntegerFieldValueGenerator());
        cachedGenerator.put(long.class,new LongFieldValueGenerator());
        cachedGenerator.put(short.class, new ShortFieldValueGenerator());
    }

    @Override
    public FieldValueGenerator getCachedGenerator(Class<?> dataType) {
        FieldValueGenerator generator = cachedGenerator.get(dataType);
        if (generator == null) {
            generator = cachedGeneratorWithSuperGenerator.get(dataType.getSuperclass());
        }

        return generator == null ? new CustomTypeFieldValueGenerator(dataType) : generator;
    }

    @Override
    public void cacheGenerator(Class<?> dataType, FieldValueGenerator generator) {
        cachedGenerator.put(dataType, generator);
    }
}
