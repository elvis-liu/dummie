package com.exmertec.dummy_easy.generator;

import java.lang.reflect.Field;

public interface FieldValueGenerator {
    Object generate(Field field);
}
