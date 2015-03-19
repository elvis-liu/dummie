package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.impl.*;
import com.google.common.collect.Lists;

import java.util.List;

public class DefaultCache extends DummyCache {
    private final List<FieldValueGenerator> cachedGenerator;

    public DefaultCache() {
        cachedGenerator = Lists.newArrayList();
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

    @Override
    public FieldValueGenerator getCachedGenerator(Class<?> dataType) {
        for (FieldValueGenerator generator: cachedGenerator) {
            if (generator.isMatchType(dataType)) {
                return generator;
            }
        }

        return new CustomTypeFieldValueGenerator(dataType);
    }

    @Override
    public void cacheGenerator(FieldValueGenerator generator) {
        cachedGenerator.add(generator);
    }
}
