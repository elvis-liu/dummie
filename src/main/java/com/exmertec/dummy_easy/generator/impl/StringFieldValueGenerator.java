package com.exmertec.dummy_easy.generator.impl;

import com.exmertec.dummy_easy.generator.TypeSafeFieldValueGenerator;

import java.lang.reflect.Field;

public class StringFieldValueGenerator extends TypeSafeFieldValueGenerator<String> {
    public StringFieldValueGenerator() {
        super(String.class);
    }

    @Override
    protected String doGenerate(Field field) {
        return field.getName();
    }
}
