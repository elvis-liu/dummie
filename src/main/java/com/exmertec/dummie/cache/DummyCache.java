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
import java.util.List;

public abstract class DummyCache implements GeneratorCache {

    private final List<FieldValueGenerator> cachedGenerator;

    protected DataCache dataCache;

    protected GenerationStrategy strategy;

    public DummyCache(GenerationStrategy strategy) {
        this(strategy, new KeyValueDataCache());
    }

    public DummyCache(GenerationStrategy strategy, DataCache dataCache) {
        this.dataCache = dataCache;
        this.strategy = strategy;

        cachedGenerator = new ArrayList<FieldValueGenerator>();
        addDefaultGenerators();
    }

    private void addDefaultGenerators() {
        cachedGenerator.add(new StringFieldValueGenerator(strategy));
        cachedGenerator.add(new ListFieldValueGenerator(strategy));
        cachedGenerator.add(new MapFieldValueGenerator(strategy));
        cachedGenerator.add(new SetFieldValueGenerator(strategy));
        cachedGenerator.add(new BooleanFieldValueGenerator(strategy));
        cachedGenerator.add(new ByteFieldValueGenerator(strategy));
        cachedGenerator.add(new CharacterFieldValueGenerator(strategy));
        cachedGenerator.add(new DoubleFieldValueGenerator(strategy));
        cachedGenerator.add(new FloatFieldValueGenerator(strategy));
        cachedGenerator.add(new IntegerFieldValueGenerator(strategy));
        cachedGenerator.add(new LongFieldValueGenerator(strategy));
        cachedGenerator.add(new ShortFieldValueGenerator(strategy));
        cachedGenerator.add(new EnumFieldValueGenerator(strategy));
    }

    public Object getCachedData(Field field) {
        Class<?> fieldType = field.getType();
        Object value = dataCache.getCachedData(fieldType, field.getName());
        if (value == null) {
            FieldValueGenerator generator = getCachedGenerator(fieldType);
            if (generator != null) {
                value = generator.generate(this, field);
            }
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
        }
        return value;
    }

    public <T> void cacheData(Class<T> dataType, String key, Object value) {
        dataCache.cacheData(dataType, key, value);
    }

    public <T> void cacheData(Class<T> clazz, Object value) {
        dataCache.cacheData(clazz, value);
    }

    @Override
    public FieldValueGenerator getCachedGenerator(Class<?> dataType) {
        for (FieldValueGenerator generator: cachedGenerator) {
            if (generator.isMatchType(dataType)) {
                return generator;
            }
        }

        return getDefaultFieldValueGenerator(dataType);
    }

    protected abstract FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType);

    @Override
    public void cacheGenerator(FieldValueGenerator generator) {
        cachedGenerator.add(generator);
    }
}
