package com.exmertec.dummie.generator;

import com.exmertec.dummie.DummyBuilder;

import java.lang.reflect.Field;

public interface FieldValueGenerator {
    Object generate(DummyBuilder builder, Field field);
}
