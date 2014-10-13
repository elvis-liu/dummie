package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.generator.TypeSafeFieldValueGenerator;

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
