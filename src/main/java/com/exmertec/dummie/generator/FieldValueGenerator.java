package com.exmertec.dummie.generator;

import com.exmertec.dummie.cache.DummyCache;

import java.lang.reflect.Field;

public interface FieldValueGenerator {
    Object generate(DummyCache cache, Field field);
    Object generate(DummyCache cache, Class<?> fieldType, String fieldName);
}
