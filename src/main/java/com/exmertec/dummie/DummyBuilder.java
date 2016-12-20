package com.exmertec.dummie;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.cache.impl.DefaultCache;
import com.exmertec.dummie.configuration.GenerationStrategy;

public class DummyBuilder<T> {
    private final Class<T> type;
    private final DummyCache cache;

    public DummyBuilder(Class<T> type) {
        this(type, new DefaultCache(GenerationStrategy.DEFAULT));
    }

    public DummyBuilder(Class<T> type, DummyCache cache) {
        this.type = type;
        this.cache = cache;
    }

    public T build() {
        try {
            T instance = type.newInstance();
            Inflater.inflateInstance(instance, cache, type);
            return instance;
        } catch (Exception e) {
            throw new DummieException(e);
        }
    }

    public <E> DummyBuilder<T> override(String key, E value) {
        cache.cacheData(value.getClass(), key, value);
        return this;
    }

    public <E> DummyBuilder<T> override(Class<E> clazz, E value) {
        cache.cacheData(clazz, value);
        return this;
    }

    public <E> DummyBuilder<T> random(Class<E> clazz) {
        cache.random(clazz);
        return this;
    }

    public <E> DummyBuilder<T> random(String key) {
        cache.random(key);
        return this;
    }
}
