package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListFieldValueGenerator extends TypeSafeFieldValueGenerator<List> {
    public ListFieldValueGenerator() {
        super(List.class);
    }

    @Override
    protected List doGenerate(DummyCache cache, Field field) {
        Type genericType = field.getGenericType();
        if (ParameterizedType.class.isInstance(genericType)) {

        } else {
            return Lists.newArrayList();
        }

        return null;
    }
}
