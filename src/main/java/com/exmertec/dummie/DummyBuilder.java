package com.exmertec.dummie;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.Inflater;

public class DummyBuilder<T> {
    private final Class<T> type;
    private final DataGenerator dataGenerator;

    public DummyBuilder(Class<T> type, DataGenerator dataGenerator) {
        this.type = type;
        this.dataGenerator = dataGenerator;
    }

    public T build() {
        try {
            T instance = type.newInstance();
            Inflater.inflateInstance(instance, dataGenerator, type);
            return instance;
        } catch (Exception e) {
            throw new DummieException(e);
        }
    }

    public <E> DummyBuilder<T> override(String key, E value) {
        dataGenerator.cacheData(value.getClass(), key, value);
        return this;
    }

    public <E> DummyBuilder<T> override(Class<E> clazz, E value) {
        dataGenerator.cacheData(clazz, value);
        return this;
    }

    public <E> DummyBuilder<T> random(Class<E> clazz) {
        dataGenerator.random(clazz);
        return this;
    }

    public <E> DummyBuilder<T> random(String key) {
        dataGenerator.random(key);
        return this;
    }
}
