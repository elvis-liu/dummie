package com.exmertec.dummie.cache;

import com.exmertec.dummie.cache.impl.KeyValueDataCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.impl.BooleanFieldValueGenerator;
import com.exmertec.dummie.generator.impl.ByteFieldValueGenerator;
import com.exmertec.dummie.generator.impl.CharacterFieldValueGenerator;
import com.exmertec.dummie.generator.impl.DoubleFieldValueGenerator;
import com.exmertec.dummie.generator.impl.EnumFieldValueGenerator;
import com.exmertec.dummie.generator.impl.FloatFieldValueGenerator;
import com.exmertec.dummie.generator.impl.IntegerFieldValueGenerator;
import com.exmertec.dummie.generator.impl.ListFieldValueGenerator;
import com.exmertec.dummie.generator.impl.LongFieldValueGenerator;
import com.exmertec.dummie.generator.impl.MapFieldValueGenerator;
import com.exmertec.dummie.generator.impl.SetFieldValueGenerator;
import com.exmertec.dummie.generator.impl.ShortFieldValueGenerator;
import com.exmertec.dummie.generator.impl.StringFieldValueGenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class DummyCache {

    private final List<FieldValueGenerator> cachedGenerator;

    protected DataCache dataCache;

    protected GenerationStrategy strategy;

    private final Set<String> randomFieldKeys;

    private final Set<Class<?>> randomFieldType;

    public DummyCache(GenerationStrategy strategy) {
        this(strategy, new KeyValueDataCache());
    }

    public DummyCache(GenerationStrategy strategy, DataCache dataCache) {
        this.dataCache = dataCache;
        this.strategy = strategy;

        cachedGenerator = new ArrayList<FieldValueGenerator>();
        randomFieldKeys = new HashSet<String>();
        randomFieldType = new HashSet<Class<?>>();

        addDefaultGenerators();
    }

    private void addDefaultGenerators() {
        cachedGenerator.add(new StringFieldValueGenerator());
        cachedGenerator.add(new ListFieldValueGenerator());
        cachedGenerator.add(new MapFieldValueGenerator());
        cachedGenerator.add(new SetFieldValueGenerator());
        cachedGenerator.add(new BooleanFieldValueGenerator());
        cachedGenerator.add(new ByteFieldValueGenerator());
        cachedGenerator.add(new CharacterFieldValueGenerator());
        cachedGenerator.add(new DoubleFieldValueGenerator());
        cachedGenerator.add(new FloatFieldValueGenerator());
        cachedGenerator.add(new IntegerFieldValueGenerator());
        cachedGenerator.add(new LongFieldValueGenerator());
        cachedGenerator.add(new ShortFieldValueGenerator());
        cachedGenerator.add(new EnumFieldValueGenerator());
    }

    public Object getCachedData(Field field) {
        Class<?> fieldType = field.getType();
        Object value = dataCache.getCachedData(fieldType, field.getName());
        if (value == null) {
            FieldValueGenerator generator = getGenerator(fieldType, field.getName());
            if (generator != null) {
                value = generator.generate(this, field);
            }
        }
        return value;
    }

    public Object getCachedData(Class<?> dataType, String key) {
        Object value = dataCache.getCachedData(dataType, key);
        if (value == null) {
            FieldValueGenerator generator = getGenerator(dataType, key);
            if (generator != null) {
                value = generator.generate(this, dataType, key);
            }
        }
        return value;
    }

    public <T> void cacheData(Class<T> dataType, String key, Object value) {
        dataCache.cacheData(dataType, key, value);
    }

    public <T> void cacheData(Class<T> clazz, Object value) {
        dataCache.cacheData(clazz, value);
    }

    public <T> void dynamicCacheData(Class<T> dataType, String key, Object value) {
        if (getStrategy(dataType, key) == GenerationStrategy.DEFAULT) {
            dataCache.cacheData(dataType, key, value);
        }
    }

    public void random(Class<?> clazz) {
        randomFieldType.add(clazz);
    }

    public void random(String key) {
        randomFieldKeys.add(key);
    }

    protected GenerationStrategy getStrategy(Class<?> dataType, String key) {
        return randomFieldType.contains(dataType) || randomFieldKeys.contains(key) ?
            GenerationStrategy.RANDOM : strategy;
    }

    protected FieldValueGenerator switchGeneratorStrategy(FieldValueGenerator generator,
                                                          Class<?> dataType, String key) {
        generator.setStrategy(getStrategy(dataType, key));
        return generator;
    }

    public FieldValueGenerator getGenerator(Class<?> dataType, String key) {
        return switchGeneratorStrategy(getCachedGenerator(dataType), dataType, key);
    }

    public FieldValueGenerator getCachedGenerator(Class<?> dataType) {
        for (FieldValueGenerator generator: cachedGenerator) {
            if (generator.isMatchType(dataType)) {
                return generator;
            }
        }

        return getDefaultFieldValueGenerator(dataType);
    }

    public void cacheGenerator(FieldValueGenerator generator) {
        cachedGenerator.add(generator);
    }

    protected abstract FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType);
}
