package com.exmertec.dummie.impl;

import com.exmertec.dummie.GeneratorFactory;
import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.generator.impl.ListFieldValueGenerator;
import com.exmertec.dummie.generator.impl.StringFieldValueGenerator;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class BasicGeneratorFactory implements GeneratorFactory {
    private final Map<Class<?>, FieldValueGenerator> generators;

    public BasicGeneratorFactory() {
        generators = Maps.newHashMap();
        addDefaultGenerators();
    }

    private void addDefaultGenerators() {
        generators.put(String.class, new StringFieldValueGenerator());
        generators.put(List.class, new ListFieldValueGenerator());
    }

    @Override
    public FieldValueGenerator getGenerator(Class<?> dataType) {
        return generators.get(dataType);
    }
}
