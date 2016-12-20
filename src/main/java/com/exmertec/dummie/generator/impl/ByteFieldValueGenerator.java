package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class ByteFieldValueGenerator extends FieldValueGenerator {

    public ByteFieldValueGenerator() {
        super(Byte.class, byte.class);
    }

    @Override
    protected Byte defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return (byte) 0;
    }

    @Override
    protected Byte randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        byte[] bytes = new byte[1];
        new Random().nextBytes(bytes);
        return bytes[0];
    }
}
