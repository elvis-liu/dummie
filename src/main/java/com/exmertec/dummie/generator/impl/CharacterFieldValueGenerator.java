package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public class CharacterFieldValueGenerator extends FieldValueGenerator {

    public CharacterFieldValueGenerator() {
        super(Character.class, char.class);
    }

    protected Character doGenerate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return '\u0000';
    }

    protected Character doGenerate(DummyCache cache, Field field) {
        return doGenerate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        return '\u0000';
    }
}
